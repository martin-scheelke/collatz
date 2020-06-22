package collatz

import java.math.BigInteger

/**
 * Wrapper method for tail-recursive Collatz conjecture sequence.
 *
 * @param n     a positive BigInteger - starting term of the Collatz sequence
 * @return number of steps for the Collatz sequence to reach 1
 * @throws IllegalArgumentException if `n < 1`
 */
@Throws(IllegalArgumentException::class)
fun collatzTailRecursive(n: BigInteger): BigInteger {
    return collatzTailRecursive(n, BigInteger("0"))
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
    require(n.compareTo(BigInteger("1")) != -1) { "n must be greater than 0" }
    val returnVal: BigInteger
    val newSteps: BigInteger

    if (n.compareTo(BigInteger("1")) == 0) {
        return steps
    }

    if (n.mod(BigInteger("2")).compareTo(BigInteger("0")) == 0) {
        returnVal = n / BigInteger("2")
        newSteps = steps + BigInteger("1")
    } else {
        //Next step will be even so optimise by combining next two steps 
        // c * 3 + 1 is an even number
        returnVal = (n * BigInteger("3") + BigInteger("1")) / BigInteger("2")
        newSteps = steps + BigInteger("2")
    }
    return collatzTailRecursive(returnVal, newSteps)
}