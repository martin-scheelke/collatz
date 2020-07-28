package collatz.data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Test;

public class ConcurrentCollatzDAOImplTest {

  private static final BigInteger COLLATZ_IN = new BigInteger("3");
  private static final BigInteger COLLATZ_OUT = new BigInteger("7");

  @Test
  void testGetAllMapTypeIsConcurrent() {
    ConcurrentCollatzDAOImpl daoImpl = new ConcurrentCollatzDAOImpl();
    Map<BigInteger, BigInteger> map = daoImpl.get();
    assertThat(map, instanceOf(ConcurrentHashMap.class));
  }

  @Test
  void testGetAll() {
    ConcurrentCollatzDAOImpl daoImpl = new ConcurrentCollatzDAOImpl();
    assertTrue(daoImpl.get().isEmpty());
    daoImpl.save(COLLATZ_IN, COLLATZ_OUT);
    assertEquals(COLLATZ_OUT, daoImpl.get().get(COLLATZ_IN));
  }

  @Test
  void testGetTerm() {
    ConcurrentCollatzDAOImpl daoImpl = new ConcurrentCollatzDAOImpl();
    daoImpl.save(COLLATZ_IN, COLLATZ_OUT);
    assertEquals(COLLATZ_OUT, daoImpl.get(COLLATZ_IN));
  }

  @Test
  void testDeleteTerm() {
    ConcurrentCollatzDAOImpl daoImpl = new ConcurrentCollatzDAOImpl();
    daoImpl.save(COLLATZ_IN, COLLATZ_OUT);
    assertEquals(COLLATZ_OUT, daoImpl.get(COLLATZ_IN));
    daoImpl.delete(COLLATZ_IN);
    assertNull(daoImpl.get(COLLATZ_IN));
  }

  @Test
  void testDelete() {
    ConcurrentCollatzDAOImpl daoImpl = new ConcurrentCollatzDAOImpl();
    daoImpl.save(COLLATZ_IN, COLLATZ_OUT);
    assertEquals(COLLATZ_OUT, daoImpl.get(COLLATZ_IN));
    daoImpl.delete();
    assertNull(daoImpl.get(COLLATZ_IN));
  }

  @Test
  void testSave() {
    ConcurrentCollatzDAOImpl daoImpl = new ConcurrentCollatzDAOImpl();
    daoImpl.save(COLLATZ_IN, COLLATZ_OUT);
    assertEquals(COLLATZ_OUT, daoImpl.get(COLLATZ_IN));
  }

  /**
   * Check for Race Condition occurs for concurrent implementation of the DAO. This is not an
   * exhaustive test - as is often the case with race conditions. It does however regularly fail
   * when test against the non-concurrent HashMap implementation of the DAO.
   *
   * @throws InterruptedException
   */
  @Test
  void testForRaceCondition() throws InterruptedException {

    int range = 30000;
    ConcurrentCollatzDAOImpl daoImpl = new ConcurrentCollatzDAOImpl();
    CountDownLatch latch = new CountDownLatch(99);

    for (long x = 0; x < range * 100; x += range) {
      BigInteger bx = BigInteger.valueOf(x);

      new Thread(() -> {
        for (int y = 1; y < range - 2; y++) {
          BigInteger by = bx.add(BigInteger.valueOf(y));
          daoImpl.save(by, bx);
        }
        latch.countDown();
      }).start();
    }

    latch.await();
    assertEquals(2999700, daoImpl.get().size());
  }
}
