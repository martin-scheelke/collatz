package collatz

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger

class CollatzTailTest {

    @Test
    fun testCollatzTailBigIntInputOutofRange() {
        Assertions.assertThrows(
                IllegalArgumentException::class.java
        ) { collatzTailRecursive(BigInteger("-1")) }
        Assertions.assertThrows(
                IllegalArgumentException::class.java
        ) { collatzTailRecursive(BigInteger("0")) }
    }

    /**
     * Collatz test with big numbers
     * <p>
     * This start term causes a StackOverflowError when not using tail recursion on my system.
     */
    @Test
    fun testCollatzTailBigIntNoStackOverFlow() {
        // Check that no stack overflow 
        Assertions.assertTrue(
                BigInteger("356470")
                        .compareTo(
                                collatzTailRecursive(BigInteger("100").pow(9999))) == 0)
    }

    @Test
    fun testCollatzTailBigIntCorrectIterations() {
        Assertions.assertTrue(
                BigInteger("0").compareTo(collatzTailRecursive(BigInteger("1"))) == 0)
        Assertions.assertTrue(
                BigInteger("1").compareTo(collatzTailRecursive(BigInteger("2"))) == 0)
        Assertions.assertTrue(
                BigInteger("7").compareTo(collatzTailRecursive(BigInteger("3"))) == 0)
        Assertions.assertTrue(
                BigInteger("19").compareTo(collatzTailRecursive(BigInteger("9"))) == 0)
        Assertions.assertTrue(
                BigInteger("118").compareTo(collatzTailRecursive(BigInteger("97"))) == 0)
        Assertions.assertTrue(
                BigInteger("986").compareTo(collatzTailRecursive(BigInteger("670617279"))) == 0)
    }
}