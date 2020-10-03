package nl.rabobank.powerofattorney.external

import nl.rabobank.powerofattorney.domain.PowerOfAttorney
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class AttorneyDataService(@Value("\${external.attorney.service.url}") private val attorneyServiceUrl: String,
                          restTemplateBuilder: RestTemplateBuilder) {

    private val restTemplateSingleAttorneyTemplate = restTemplateBuilder.build()

    fun getPowerOfAttorneys(): List<PowerOfAttorney> {
        val responseEntity: ResponseEntity<Array<Any>> = restTemplateSingleAttorneyTemplate.getForEntity(attorneyServiceUrl, Array<Any>::class.java)
        val objects = responseEntity.body
        val result = arrayListOf<PowerOfAttorney>()
        for (o in objects) {
            val powerOfAttorneyReference = o as LinkedHashMap<String, Any>
            result.add(getPowerOfAttorneyById(powerOfAttorneyReference["id"] as String))
        }
        return result
    }

    fun getPowerOfAttorneyById(id: String): PowerOfAttorney {
        return restTemplateSingleAttorneyTemplate.getForObject<PowerOfAttorney>("$attorneyServiceUrl/$id", PowerOfAttorney::class.java)
    }
}