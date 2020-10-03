package nl.rabobank.powerofattorney.controller

import nl.rabobank.powerofattorney.model.FullPowerOfAttorney
import nl.rabobank.powerofattorney.service.FullAttorneyDataService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class PowerOfAttorneyController(val fullAttorneyDataService: FullAttorneyDataService) {
    private val logger = LoggerFactory.getLogger(PowerOfAttorneyController::class.java)

    @GetMapping("/enriched-attorneys")
    fun getFullPowerOfAttorneys(): List<FullPowerOfAttorney> {
        logger.info("Retrieving full power of attorneys")
        return fullAttorneyDataService.getFullAttorneyData()
    }

    @GetMapping("/enriched-attorneys/{id}")
    fun getSingleFullPowerOfAttorney(@PathVariable id: String): FullPowerOfAttorney {
        logger.info("Retrieving full power of attorneys for ID $id")
        return fullAttorneyDataService.getSingleFullAttorneyData(id)
    }
}