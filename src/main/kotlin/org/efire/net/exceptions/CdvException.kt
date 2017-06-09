package org.efire.net.exceptions

import org.efire.net.model.BankBranch

/**
 * Created by DELFIN.TENERIFE on 6/9/2017.
 */
interface CdvException {
    fun check(accountNumber: String, bankBranch: BankBranch) : Boolean
    fun isValidAccount(accountNumber : String) : Boolean {
        return false
    }
}