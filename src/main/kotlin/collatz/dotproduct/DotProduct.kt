package collatz.dotproduct

/**
 * Dot Product of two vectors.
 * <p>
 * Method:
 *      Zip the input vectors into a vector of pairs.
 *      Map the pairs by multiplying them together.
 *      Reduce the terms of the map output into the dot product by summing terms.
 *
 * @param v1  an Array of doubles - the first input vector.
 * @param v2  an Array of doubles - the second input vector.
 * @return the Dot Product of the two input vectors.
 */
fun dotProduct(v1: Array<Double>, v2: Array<Double>) =
        v1.zip(v2)
                .map { it.first * it.second }
                .reduce { a, b -> a + b }