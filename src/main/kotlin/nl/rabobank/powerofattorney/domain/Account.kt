package nl.rabobank.powerofattorney.domain

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include
import java.util.Date

@JsonInclude(Include.NON_NULL)
data class Account(
        val owner: String,
        val balance: Long,
        @JsonFormat(pattern = "dd-MM-yyy")
        val created: Date,
        @JsonFormat(pattern = "dd-MM-yyy")
        val ended: Date?)
