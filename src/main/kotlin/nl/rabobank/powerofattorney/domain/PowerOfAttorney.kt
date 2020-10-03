package nl.rabobank.powerofattorney.domain

/**
 * The power of attorney represent the 'compact' attorney; this is retrieved by the 'backend' mock service; it
 * has account number (not the actual account) and card numbers (not the card details).
 */
data class PowerOfAttorney(
        val id: String,
        val grantor: String,
        val grantee: String,
        val account: String,
        val direction: Direction,
        val authorizations: Set<Authorization>,
        val cards: List<Card>?)
