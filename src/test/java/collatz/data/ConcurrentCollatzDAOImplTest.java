package collatz.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigInteger;
import org.junit.jupiter.api.Test;

public class ConcurrentCollatzDAOImplTest {

  private static final BigInteger COLLATZ_IN = new BigInteger("3");
  private static final BigInteger COLLATZ_OUT = new BigInteger("7");

  @Test
  void testGetAll() {
    ConcurrentCollatzDAOImpl daoImpl = new ConcurrentCollatzDAOImpl();
    daoImpl.get().isEmpty();
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
}
