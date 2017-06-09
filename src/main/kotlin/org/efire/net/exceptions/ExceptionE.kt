package org.efire.net.exceptions

import org.efire.net.model.BankBranch
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created by DELFIN.TENERIFE on 6/9/2017.
 */
class ExceptionE : CdvException {
    private val LOG : Logger = LoggerFactory.getLogger(javaClass)

    override fun check(accountNumber: String, bankBranch: BankBranch): Boolean {
        LOG.info("Validating...")
        val check = bankBranch.computeCheckDigit(accountNumber)
        return 0 == check && isValidAccount(accountNumber)
    }

    override fun isValidAccount(accountNumber: String): Boolean {
        val len = accountNumber.length
        return ('0' <  accountNumber.codePointAt(len - 1).toChar() || '0' < accountNumber.codePointAt(len - 2).toChar()) &&
        '0' == accountNumber.codePointAt(0).toChar() && '0' < accountNumber.codePointAt(1).toChar()
    }
}