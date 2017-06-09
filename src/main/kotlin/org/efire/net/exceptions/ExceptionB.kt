package org.efire.net.exceptions

import org.efire.net.model.BankBranch
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Created by DELFIN.TENERIFE on 6/9/2017.
 */
class ExceptionB : CdvException {
    private val LOG : Logger = LoggerFactory.getLogger(javaClass)

    override fun check(accountNumber: String, bankBranch: BankBranch) : Boolean {
        LOG.info("Validating...")
        val check = bankBranch.computeCheckDigit(accountNumber)
        return 0 == check || (1 == check && isValidAccount(accountNumber))
    }

    override fun isValidAccount(accountNumber: String): Boolean {
        val leastSignificantDigit = accountNumber[accountNumber.length - 1]
        return when(leastSignificantDigit) {'1', '0' -> true else -> false }
    }
}