# Technical Assignment Eelco den Heijer
## Setup
You need Java 11 and Maven 3 to set it up. You can build the entire solution with the following command

`mvn clean install`

If you want to run the solution, first start the 'backend mock services' with

`mvn exec:java`

and then start the integrated web service with the following command

`mvn spring-boot:run`

I implemented two endpoints that integrates the data from all mock services. The endpoints are:

[https://localhost:8443/enriched-attorneys/0001](https://localhost:8443/enriched-attorneys/0001)

For a single full attorney (in this case, with ID '0001') and

[https://localhost:8443/enriched-attorneys](https://localhost:8443/enriched-attorneys)

for all attorneys.

# Solution
I built the entire solution in Kotlin, since that was allowed, and I think Kotlin is more compact than Java (and thus I am slightly faster in Kotlin than in Java). Furthermore, Kotlin is a JVM language, so I mostly used existing Java libraries. I used Spring Boot for the REST services and Jackson for JSON to Object mapping (although most is done by Spring REST template). I configured Spring Boot to use SSL, I used a self-signed certificate that is more or less refused by Chrome, but it works in Firefox (after you accept the fact that it's a self-signed certificate).

I disregarded the automated tests; normally I wouldn't do that, I usually strive for 90 to 95% test coverage, but since this is not intended to be production code, I omitted it. Implementing unit tests would have been easy to do, but would have been rather time-consuming.
