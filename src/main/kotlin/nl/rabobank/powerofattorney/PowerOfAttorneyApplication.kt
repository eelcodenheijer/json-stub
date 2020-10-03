package nl.rabobank.powerofattorney

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class PowerOfAttorneyApplication

fun main(args: Array<String>) {
    SpringApplication.run(PowerOfAttorneyApplication::class.java, *args)
}