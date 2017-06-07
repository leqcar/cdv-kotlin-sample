package org.efire.net.model

/**
 * Created by jongtenerife on 08/06/2017.
 */

data class BankBranch(val accountIndicator: Int,
                      val weightingDigits: String,
                      val fudgeFactor: Int,
                      val modulus: Int) {


    private val DIGITS_REGEX = "\\d+"

    fun computeCheckDigit(accountNumber: String) : Int {

        val weightingNumbers = weightingDigitsList()

        return 0
    }

    /**
     * Gets the weighting digits list.
     *
     */
    fun weightingDigitsList() : List<Int>? {

        val wd = weightingDigits.length
        if (wd.rem(2) == 1)
            throw IllegalArgumentException("Invalid Weighting Digits length $wd. Length should be an even number.")

        if (DIGITS_REGEX.toRegex().matches(weightingDigits).not())
            throw IllegalArgumentException("Invalid Weighting Digits $weightingDigits. Must be numeric.")

        val weightingDigitsNumbers = mutableListOf(wd/2)
        var i = 0
        while (i <= wd - 2)
        {
            val x = weightingDigits.substring(i, i + 2).toInt()
            weightingDigitsNumbers.add(x)
            i += 2
        }
        return weightingDigitsNumbers
    }

}