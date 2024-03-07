package com.vaja1.microservice1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class Microservice1Application

fun main(args: Array<String>) {
    runApplication<Microservice1Application>(*args)
}
