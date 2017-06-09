package org.efire.net.exceptions

import org.efire.net.model.BankBranch
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created by DELFIN.TENERIFE on 6/9/2017.
 */
class ExceptionD : CdvException{
    private val LOG : Logger = LoggerFactory.getLogger(javaClass)

    override fun check(accountNumber: String, bankBranch: BankBranch): Boolean {
        LOG.info("Validating...")
        val check = bankBranch.computeCheckDigit(accountNumber)
        return 0 == check || checkDigitLen(check, accountNumber)
    }

    private fun checkDigitLen(check: Int, accountNumber: String): Boolean {
        return 1 == check && isValidAccount(accountNumber)
    }

    override fun isValidAccount(accountNumber: String): Boolean {
        val len: Int = accountNumber.length
        return 11 == len && ('1' == accountNumber.codePointAt(len - 1).toChar() || '0' == accountNumber.codePointAt(len - 1).toChar())
                || '0' == accountNumber.codePointAt(0).toChar()
    }
}