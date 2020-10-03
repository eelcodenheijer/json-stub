package nl.rabobank.powerofattorney.service

import nl.rabobank.powerofattorney.domain.CardType
import nl.rabobank.powerofattorney.domain.PowerOfAttorney
import nl.rabobank.powerofattorney.external.AccountDataService
import nl.rabobank.powerofattorney.external.AttorneyDataService
import nl.rabobank.powerofattorney.external.CreditCardService
import nl.rabobank.powerofattorney.external.DebitCardService
import nl.rabobank.powerofattorney.model.CreditCard
import nl.rabobank.powerofattorney.model.DebitCard
import nl.rabobank.powerofattorney.model.FullPowerOfAttorney
import org.springframework.stereotype.Service

@Service
class FullAttorneyDataService(private val attorneyDataService: AttorneyDataService,
                              private val accountDataDataService: AccountDataService,
                              private val creditCardService: CreditCardService,
                              private val debitCardService: DebitCardService) {

    fun getFullAttorneyData(): List<FullPowerOfAttorney> {
        val powerOfAttorneys = attorneyDataService.getPowerOfAttorneys()
        val result = mutableListOf<FullPowerOfAttorney>()
        for (poa in powerOfAttorneys) {
            result.add(getData(poa))
        }
        return result
    }

    fun getSingleFullAttorneyData(id: String): FullPowerOfAttorney {
        val powerOfAttorney = attorneyDataService.getPowerOfAttorneyById(id)
        return getData(powerOfAttorney)
    }

    private fun getData(poa: PowerOfAttorney): FullPowerOfAttorney {
        // 1. retrieve debit and credit cards
        val debitCards = mutableListOf<DebitCard>()
        val creditCards = mutableListOf<CreditCard>()
        // Get full card data...
        if (!poa.cards.isNullOrEmpty()) {
            for (card in poa.cards) {
                when (card.type) {
                    CardType.DEBIT_CARD -> {
                        debitCards.add(debitCardService.getDebitCard(card.id))
                    }
                    CardType.CREDIT_CARD -> {
                        creditCards.add(creditCardService.getCreditCard(card.id))
                    }
                }
            }
        }
        // get account data
        val account = accountDataDataService.getAccount(poa.account)

        return FullPowerOfAttorney(id = poa.id,
                grantor = poa.grantor,
                grantee = poa.grantee,
                account = account,
                direction = poa.direction,
                authorizations = poa.authorizations,
                debitCards = debitCards,
                creditCards = creditCards
        )
    }
}