package nl.rabobank.powerofattorney.model


data class CreditCard(
        override val id: String,
        override val cardNumber: String,
        override val sequenceNumber: Int,
        override val cardHolder: String,
        val monthlyLimit: Int
) : Card