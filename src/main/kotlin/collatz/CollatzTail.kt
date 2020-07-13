package collatz

import java.math.BigInteger

val ZERO = BigInteger("0");
val ONE = BigInteger("1");
val TWO = BigInteger("2");
val THREE = BigInteger("3");

/**
 * Wrapper method for tail-recursive Collatz conjecture sequence.
 *
 * @param n     a positive BigInteger - starting term of the Collatz sequence
 * @return number of steps for the Collatz sequence to reach 1
 * @throws IllegalArgumentException if `n < 1`
 */
@Throws(IllegalArgumentException::class)
fun collatzTailRecursive(n: BigInteger): BigInteger {
    return collatzTailRecursive(n, ZERO)
}

/**
 * Tail-recursive Collatz conjecture sequence implemented with BigInteger. Allowing arbitrary length
 * inputs, outputs and interim terms. Kotlin allows tail-recursion optimisation (tailrec keyword)
 * to prevent stack overflows on deep recursion.
 *
 * @param n     a positive BigInteger - current term of the Collatz sequence
 * @param steps number of steps used to reach current term
 * @return number of steps for the Collatz sequence to reach 1
 * @throws IllegalArgumentException if `n < 1`
 */
@Throws(IllegalArgumentException::class)
private tailrec fun collatzTailRecursive(n: BigInteger, steps: BigInteger): BigInteger {
    require(n.compareTo(ONE) != -1) { "n must be greater than 0" }
    val returnVal: BigInteger
    val newSteps: BigInteger

    if (n.compareTo(ONE) == 0) {
        return steps
    }

    if (n.mod(TWO).compareTo(ZERO) == 0) {
        returnVal = n / TWO
        newSteps = steps + ONE
    } else {
        //Next step will be even so optimise by combining next two steps 
        // c * 3 + 1 is an even number
        returnVal = (n * THREE + ONE) / TWO
        newSteps = steps + TWO
    }
    return collatzTailRecursive(returnVal, newSteps)
}