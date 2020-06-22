package collatz.calc;

/**
 * Simple recursive Implementation of the Collatz conjecture sequence.
 * See <a href="wikipedia.org/wiki/Collatz_conjecture">  wiki/Collatz_conjecture</a>.
 */
public class Collatz {

  /**
   * Simple recursive Collatz conjecture sequence. Vulnerable to stack overflow.
   *
   * @param n a positive long - starting term of the Collatz sequence
   * @return number of steps for the Collatz sequence to reach 1
   * @throws IllegalArgumentException if {@code n < 1}
   * @throws IllegalStateException    if an interim term is > {@link Long#MAX_VALUE}
   */
  public static long collatzRecursive(long n)
    throws IllegalArgumentException, IllegalStateException {
    if (n < 1) {
      throw new IllegalArgumentException("n must be greater than 0");
    }
    if (n == 1) {
      return 0;
    } else {
      if (n % 2 == 0) {
        return 1 + collatzRecursive(n / 2);
      } else {
        if (n > Long.MAX_VALUE / 3) {
          throw new IllegalStateException("Interim term greater than Long.MAX_VALUE");
        } else {
          //Next step will be even so optimise by combining next two steps 
          return 2 + (collatzRecursive((n * 3 + 1) / 2)); // c * 3 + 1 is an even number
        }
      }
    }
  }
}