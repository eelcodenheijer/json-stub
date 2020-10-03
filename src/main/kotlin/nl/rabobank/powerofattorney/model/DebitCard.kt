package nl.rabobank.powerofattorney.model

import nl.rabobank.powerofattorney.domain.Limit

data class DebitCard(
        override val id: String,
        override val cardNumber: String,
        override val sequenceNumber: Int,
        override val cardHolder: String,
        val atmLimit: Limit,
        val posLimit: Limit,
        val contactless: Boolean
) : Card