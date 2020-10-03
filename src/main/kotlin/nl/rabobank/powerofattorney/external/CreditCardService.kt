package nl.rabobank.powerofattorney.external

import nl.rabobank.powerofattorney.model.CreditCard
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service

@Service
class CreditCardService(@Value("\${external.creditcard.service.url}") creditCardService: String,
                        restTemplateBuilder: RestTemplateBuilder) {
    private val restTemplateSingleAttorneyTemplate = restTemplateBuilder.rootUri(creditCardService).build()

    fun getCreditCard(id: String): CreditCard {
        return restTemplateSingleAttorneyTemplate.getForObject<CreditCard>("/$id", CreditCard::class.java)
    }
}