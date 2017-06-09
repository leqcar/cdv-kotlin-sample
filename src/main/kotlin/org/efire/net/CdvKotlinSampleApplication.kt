package org.efire.net

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class CdvKotlinSampleApplication

fun main(args: Array<String>) {
    SpringApplication.run(CdvKotlinSampleApplication::class.java, *args)
}
