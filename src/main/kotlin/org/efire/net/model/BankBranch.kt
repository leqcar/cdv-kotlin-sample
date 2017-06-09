package org.efire.net.model

/**
 * Created by jongtenerife on 08/06/2017.
 */

data class BankBranch(val accountIndicator: Int = 0,
                      val weightingDigits: String = "",
                      val fudgeFactor: Int = 0,
                      val modulus: Int = 0) {


    private val DIGITS_REGEX = "\\d+"
    private val EXCEPTION_CHECK = -2

    fun computeCheckDigit(accountNumber: String) : Int {

        if (isException(modulus)) return EXCEPTION_CHECK

        val weightingNumbers = weightingDigitsList()
        val accountNumbers = accountNumberDigits(accountNumber)
        var check: Int = (0..weightingNumbers.size - 1).sumBy { weightingNumbers[it] * accountNumbers[it] }

        check += fudgeFactor
        return check.rem(modulus)
    }

    fun isException(m: Int?) : Boolean {
        return when(m) { null, 0 -> true else -> false }
    }
    /**
     * Gets the weighting digits list.
     *
     */
    fun weightingDigitsList() : List<Int> {

        val wdlen = weightingDigits.length
        if (wdlen.rem(2) == 1)
            throw IllegalArgumentException("Invalid Weighting Digits length $wdlen. Length should be an even number.")

        if (isNotDecimalNumber(weightingDigits))
            throw IllegalArgumentException("Invalid Weighting Digits $weightingDigits. Must be numeric.")

        val weightingDigitsNumbers = mutableListOf(wdlen/2)
        var i = 0
        while (i <= wdlen - 2)
        {
            val x = weightingDigits.substring(i, i + 2).toInt()
            weightingDigitsNumbers.add(x)
            i += 2
        }
        return weightingDigitsNumbers
    }

    fun accountNumberDigits(accountNumber: String) : List<Int>{
        if (isNotDecimalNumber(accountNumber))
            throw IllegalArgumentException("Invalid Account Number $accountNumber. Must be numeric.")

        val weightingNumbers = weightingDigitsList()
        var accountNumbers = arrayListOf<Int>()
        accountNumber.toCharArray().mapTo(accountNumbers) { it.toInt() }

        if (weightingNumbers.size < accountNumbers.size)
            throw IllegalArgumentException("Invalid Account No. Length should be less than or equal the length of Weighting Digits.")

        while (weightingNumbers.size > accountNumbers.size) {
            accountNumbers.add(0, 0)
        }
        return accountNumbers
    }

    fun isNotDecimalNumber(valString: String) : Boolean {
        if (DIGITS_REGEX.toRegex().matches(valString).not())
            return true
        return false
    }
}
