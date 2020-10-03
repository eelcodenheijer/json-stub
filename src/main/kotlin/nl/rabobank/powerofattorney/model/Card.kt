package nl.rabobank.powerofattorney.model

interface Card {
    val id: String
    val cardNumber: String
    val sequenceNumber: Int
    val cardHolder: String
}
