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

        val wdLength = weightingDigits.length

        //Semantic Validation
        check(wdLength.rem(2) == 0, { "Invalid Weighting Digits length $wdLength. Length should be an even number." })
        check(isDecimalNumber(weightingDigits), { "Invalid Weighting Digits $weightingDigits. Must be numeric." })

        val weightingDigitsNumbers = mutableListOf(wdLength/2)
        var i = 0
        while (i <= wdLength - 2)
        {
            val x = weightingDigits.substring(i, i + 2).toInt()
            weightingDigitsNumbers.add(x)
            i += 2
        }
        return weightingDigitsNumbers
    }

    fun accountNumberDigits(accountNumber: String) : List<Int>{
        require(isDecimalNumber(accountNumber), { "Invalid Account Number $accountNumber. Must be numeric." } )

        val weightingNumbers = weightingDigitsList()
        var accountNumbers = arrayListOf<Int>()
        accountNumber.toCharArray().mapTo(accountNumbers) { it.toInt() }

        check(accountNumbers.size < weightingNumbers.size,
                { "Invalid Account No. Length should be less than or equal the length of Weighting Digits." })

        while (weightingNumbers.size > accountNumbers.size) {
            accountNumbers.add(0, 0)
        }
        return accountNumbers
    }

    fun isDecimalNumber(valString: String) : Boolean {
        if (DIGITS_REGEX.toRegex().matches(valString))
            return true
        return false
    }
}
