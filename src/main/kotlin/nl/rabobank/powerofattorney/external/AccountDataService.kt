package nl.rabobank.powerofattorney.external

import nl.rabobank.powerofattorney.domain.Account
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.Date

@Service
class AccountDataService(@Value("\${external.account.service.url}") accountService: String,
                         restTemplateBuilder: RestTemplateBuilder) {
    private val restTemplateAccount = restTemplateBuilder.rootUri(accountService).build()
    private val logger = LoggerFactory.getLogger(AccountDataService::class.java)

    fun getAccount(iban: String): Account? {
        // there is a bug in retrieving the accounts from the account service; the account service only accepts 'old' style account numbers, not IBANs
        val shortAccountNumber = iban.substring(8)
        logger.info("Retrieving Account for IBAN $iban ($shortAccountNumber)")
        val anyResponse = restTemplateAccount.getForEntity("/$shortAccountNumber", Any::class.java)
        val map = anyResponse.body as Map<String, Any>
        val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy")
        // Jackson does not act nicely with the Account Kotlin data class, so convert the JSON manually to an Account object
        val account = Account(owner = map["owner"].toString(),
                balance = map["balance"].toString().toLong(),
                created = simpleDateFormat.parse(map["created"].toString()),
                ended = if (map["ended"] != null) simpleDateFormat.parse(map["ended"].toString()) else null
        )
        if (account.ended != null && account.ended.before(Date())) {
            logger.info("Account ended on ${account.ended}, not returning")
            return null
        }
        return account
    }
}