package collatz.data;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class CollatzDAOImpl implements CollatzDAO {

  HashMap<BigInteger, BigInteger> collatzStore;

  public CollatzDAOImpl() {
    this.collatzStore = new HashMap<BigInteger, BigInteger>();
  }

  @Override
  public Optional<Map<BigInteger, BigInteger>> get() {
    return Optional.of((Map) new ConcurrentHashMap(collatzStore));
  }

  @Override
  public void delete(BigInteger startTerm) {
    collatzStore.remove(startTerm);
  }

  @Override
  public void delete() {
    collatzStore.clear();
  }

  @Override
  public BigInteger get(BigInteger startTerm) {
    return null;
  }

  @Override
  public void save(BigInteger startTerm, BigInteger value) {
    collatzStore.put(startTerm, value);
  }
}
