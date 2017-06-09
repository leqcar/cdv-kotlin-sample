package org.efire.net.config

import org.efire.net.exceptions.*
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

    @Bean
    open fun cdvExceptionF() : CdvException {
        return ExceptionF()
    }

    @Bean("exceptions")
    open fun exceptions() : MutableMap<String, CdvException> {
        var exceptions = mutableMapOf(
                "b" to cdvExceptionB(),
                "d" to cdvExceptionD(),
                "e" to cdvExceptionE(),
                "f" to cdvExceptionF())
        return exceptions
    }
}

