package collatz.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import collatz.data.CollatzDAOImpl;
import collatz.service.Service;
import collatz.service.ServiceImpl;
import java.math.BigInteger;
import java.util.Optional;
import org.junit.jupiter.api.Test;

/**
 * Integration tests of Service layer
 */
public class ServiceImplTest {

  private static final BigInteger COLLATZ_IN = new BigInteger("3");
  private static final BigInteger COLLATZ_OUT = new BigInteger("7");
  private static final int COLLATZ_WAIT_MS = 500;

  @Test
  public void testCalcNewCollatz() {
    Service service = new ServiceImpl(new CollatzDAOImpl());
    Optional<BigInteger> result = service.calcCollatzAsync(COLLATZ_IN);
    assertTrue(result.isEmpty());
  }

  @Test
  public void testCalcCompletedCollatz() throws InterruptedException {
    Service service = new ServiceImpl(new CollatzDAOImpl());
    service.calcCollatzAsync(COLLATZ_IN);
    Thread.sleep(COLLATZ_WAIT_MS);
    Optional<BigInteger> result = service.calcCollatzAsync(COLLATZ_IN);
    assertTrue(result.isPresent() && result.get().equals(COLLATZ_OUT));
  }

  @Test
  public void testCalcCompletedGetCollatz() throws InterruptedException {
    Service service = new ServiceImpl(new CollatzDAOImpl());
    service.calcCollatzAsync(COLLATZ_IN);
    Thread.sleep(COLLATZ_WAIT_MS);
    Optional<BigInteger> result = service.getCollatz(COLLATZ_IN);
    assertTrue(result.isPresent() && result.get().equals(COLLATZ_OUT));
  }

  @Test
  public void testCalcCompletedDeleteCollatz() throws InterruptedException {
    Service service = new ServiceImpl(new CollatzDAOImpl());
    service.calcCollatzAsync(COLLATZ_IN);

    Thread.sleep(COLLATZ_WAIT_MS);

    Optional<BigInteger> result = service.getCollatz(COLLATZ_IN);
    assertTrue(result.isPresent() && result.get().equals(COLLATZ_OUT));

    service.deleteCollatz(COLLATZ_IN);

    Optional<BigInteger> resultAfterDelete = service.getCollatz(COLLATZ_IN);
    assertTrue(resultAfterDelete.isEmpty());
  }
}