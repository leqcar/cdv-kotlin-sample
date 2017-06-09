package org.efire.net.resource

import org.efire.net.exceptions.CdvException
import org.efire.net.model.BankBranch
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.*

/**
 * Created by DELFIN.TENERIFE on 6/9/2017.
 */
@RestController
@RequestMapping("api/cdv")
class CdvController {

    @Autowired
    @Qualifier("exceptions")
    lateinit var exception: Map<String, CdvException>

    private val LOG : Logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/{accountNumber}/{exceptionCode}/verify")
    fun verifyByException(@PathVariable accountNumber: String,
                          @PathVariable exceptionCode: String,
                          @RequestBody bankBranch: BankBranch) {

        val result = exception[exceptionCode]?.check(accountNumber, bankBranch)
        // TODO  Return a Response Entity
        LOG.info("Result: $result")
    }
}


