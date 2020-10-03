package nl.rabobank.powerofattorney.external

import nl.rabobank.powerofattorney.model.DebitCard
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Service

@Service
class DebitCardService(@Value("\${external.debitcard.service.url}") debitCardService: String,
                       restTemplateBuilder: RestTemplateBuilder) {
    private val restTemplateSingleAttorneyTemplate = restTemplateBuilder.rootUri(debitCardService).build()

    fun getDebitCard(id: String): DebitCard {
        return restTemplateSingleAttorneyTemplate.getForObject("/$id", DebitCard::class.java)
    }
}