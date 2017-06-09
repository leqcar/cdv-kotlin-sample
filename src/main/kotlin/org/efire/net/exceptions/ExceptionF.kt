package org.efire.net.exceptions

import org.efire.net.model.BankBranch

/**
 * Created by jongtenerife on 10/06/2017.
 */
class ExceptionF : CdvException {

    override fun check(accountNumber: String, bankBranch: BankBranch): Boolean {
        return 0 == bankBranch.computeCheckDigit(accountNumber) || isValidAccount(accountNumber)
    }

    override fun isValidAccount(accountNumber: String): Boolean {
        val exceptionFs = exceptionBankBranches()
        if (isAnyExceptionEquateToZero(exceptionFs, accountNumber)) return true
        if (isExceptionF4(accountNumber, exceptionFs)) return true
        if (isExceptionF5LeastSignificant(accountNumber, exceptionFs)) return true
        return false
    }

    private fun isAnyExceptionEquateToZero(exceptionFs: Map<String, BankBranch>, accountNumber: String): Boolean {
        return exceptionFs
                .filter { x -> 0 == x.component2().computeCheckDigit(accountNumber) }
                .any()
    }

    private fun isExceptionF4(accountNumber: String, exceptionFs: Map<String, BankBranch>) : Boolean {
        val length = accountNumber.length
        return isCheckException(
                accountNumber,
                exceptionFs["exceptionF4"],
                { x -> (x == 0 || (length == 10 || length == 11)) &&
                        x == 1 &&
                        (accountNumber.codePointAt(length - 1).toChar() == '0').or(
                                accountNumber.codePointAt(length - 1).toChar() == '1')
                }
        )
    }

    private fun isExceptionF5LeastSignificant(accountNumber: String, exceptionFs: Map<String, BankBranch>): Boolean {
        val length = accountNumber.length
        if (length < 10) {
            val leastSignificant = Integer.parseInt(accountNumber.substring(length - 1)) + 6
            val substitute = accountNumber.substring(0, length - 1) + leastSignificant % 10
            return isCheckException(substitute,
                    exceptionFs["exceptionF5"],
                    { x -> x == 0 })
        }
        return false
    }

    private fun isCheckException(accountNumber: String, bankBranch: BankBranch?, body: (Int) -> Boolean) : Boolean {
        val check = bankBranch?.computeCheckDigit(accountNumber)
        if (body(check!!)) {
            return true
        }
        return false
    }

    fun exceptionBankBranches() : Map<String, BankBranch> {
        val ebb = hashMapOf<String, BankBranch>()
        ebb.put("exceptionF2", BankBranch(0, "0107030209080704030201",0,11))
        ebb.put("exceptionF3", BankBranch(0, "0104030207060504030201",0,11))
        ebb.put("exceptionF4", BankBranch(0, "0504030207060504030201",0,11))
        ebb.put("exceptionF5", BankBranch(0, "0101030207060504030201",0,11))
        ebb.put("exceptionF6", BankBranch(0, "0104030209080704030201",0,11))
        return ebb
    }

}