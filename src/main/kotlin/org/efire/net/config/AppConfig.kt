package org.efire.net.config

import org.efire.net.exceptions.CdvException
import org.efire.net.exceptions.ExceptionB
import org.efire.net.exceptions.ExceptionD
import org.efire.net.exceptions.ExceptionE
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Created by DELFIN.TENERIFE on 6/9/2017.
 */
@Configuration
open class AppConfig {

    @Bean
    open fun cdvExceptionB() : CdvException {
        return ExceptionB()
    }

    @Bean
    open fun cdvExceptionD() : CdvException {
        return ExceptionD()
    }

    @Bean
    open fun cdvExceptionE() : CdvException {
        return ExceptionE()
    }

    @Bean("exceptions")
    open fun exceptions() : MutableMap<String, CdvException> {
        var exceptions = mutableMapOf("b" to cdvExceptionB(),
                "d" to cdvExceptionD(),
                "e" to cdvExceptionE())
        return exceptions
    }
}

