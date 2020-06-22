package collatz.dotproduct

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DotProductTest {

    @Test
    fun testDotProduct() {
        assertEquals(3.0,
                dotProduct(arrayOf(2.0, -3.0, -5.0), arrayOf(1.0, -2.0, 1.0))
        )
        assertEquals(8.0,
                dotProduct(arrayOf(2.0, -3.0, -5.0), arrayOf(1.0, -2.0))
        )
        assertEquals(0.0,
                dotProduct(arrayOf(2.0, -3.0, -5.0), arrayOf(0.0, 0.0, 0.0))
        )
        assertEquals(8.0,
                dotProduct(arrayOf(2.0, -3.0), arrayOf(1.0, -2.0, 1.0))
        )
    }
}