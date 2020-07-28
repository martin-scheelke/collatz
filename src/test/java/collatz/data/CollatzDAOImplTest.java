package collatz.data;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class CollatzDAOImplTest {

  private static final BigInteger COLLATZ_IN = new BigInteger("3");
  private static final BigInteger COLLATZ_OUT = new BigInteger("7");

  @Test
  void testGetAllMapTypeIsNotConcurrent() {
    CollatzDAOImpl daoImpl = new CollatzDAOImpl();
    Map<BigInteger, BigInteger> map = daoImpl.get();
    assertThat(map, instanceOf(HashMap.class));
  }

  @Test
  void testGetAll() {
    CollatzDAOImpl daoImpl = new CollatzDAOImpl();
    assertTrue(daoImpl.get().isEmpty());
    daoImpl.save(COLLATZ_IN, COLLATZ_OUT);
    assertEquals(COLLATZ_OUT, daoImpl.get().get(COLLATZ_IN));
  }

  @Test
  void testGetTerm() {
    CollatzDAOImpl daoImpl = new CollatzDAOImpl();
    daoImpl.save(COLLATZ_IN, COLLATZ_OUT);
    assertEquals(COLLATZ_OUT, daoImpl.get(COLLATZ_IN));
  }

  @Test
  void testDeleteTerm() {
    CollatzDAOImpl daoImpl = new CollatzDAOImpl();
    daoImpl.save(COLLATZ_IN, COLLATZ_OUT);
    assertEquals(COLLATZ_OUT, daoImpl.get(COLLATZ_IN));
    daoImpl.delete(COLLATZ_IN);
    assertNull(daoImpl.get(COLLATZ_IN));
  }

  @Test
  void testDelete() {
    CollatzDAOImpl daoImpl = new CollatzDAOImpl();
    daoImpl.save(COLLATZ_IN, COLLATZ_OUT);
    assertEquals(COLLATZ_OUT, daoImpl.get(COLLATZ_IN));
    daoImpl.delete();
    assertNull(daoImpl.get(COLLATZ_IN));
  }

  @Test
  void testSave() {
    CollatzDAOImpl daoImpl = new CollatzDAOImpl();
    daoImpl.save(COLLATZ_IN, COLLATZ_OUT);
    assertEquals(COLLATZ_OUT, daoImpl.get(COLLATZ_IN));
  }
}
