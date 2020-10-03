package nl.rabobank.powerofattorney.model

import com.fasterxml.jackson.annotation.JsonInclude
import nl.rabobank.powerofattorney.domain.Account
import nl.rabobank.powerofattorney.domain.Authorization
import nl.rabobank.powerofattorney.domain.Direction

/**
 * The full power of attorney represent the entire, enriched power of attorney; this is returned by our service.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
data class FullPowerOfAttorney(
        val id: String,
        val grantor: String,
        val grantee: String,
        val account: Account?,
        val direction: Direction,
        val authorizations: Set<Authorization>,
        val debitCards: List<DebitCard>,
        val creditCards: List<CreditCard>
)