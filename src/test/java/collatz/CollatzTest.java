package collatz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import collatz.calc.Collatz;
import org.junit.jupiter.api.Test;

public class CollatzTest {

  @Test
  public void testCollatzInputOutofRange() throws IllegalArgumentException {
    assertThrows(IllegalArgumentException.class, () -> Collatz.collatzRecursive(-1));
    assertThrows(IllegalArgumentException.class, () -> Collatz.collatzRecursive(0));
  }

  @Test
  public void testCollatzInterimTermOutofRange() throws IllegalStateException {
    assertThrows(IllegalStateException.class, () -> Collatz.collatzRecursive(Long.MAX_VALUE));
  }

  @Test
  public void testCollatzCorrectIterations() {
    assertEquals(0, Collatz.collatzRecursive(1));
    assertEquals(1, Collatz.collatzRecursive(2));
    assertEquals(7, Collatz.collatzRecursive(3));
    assertEquals(19, Collatz.collatzRecursive(9));
    assertEquals(118, Collatz.collatzRecursive(97));
    assertEquals(986, Collatz.collatzRecursive(670617279));
  }
}