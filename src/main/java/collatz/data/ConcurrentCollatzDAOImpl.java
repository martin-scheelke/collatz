package collatz.data;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCollatzDAOImpl implements CollatzDAO {

  ConcurrentHashMap<BigInteger, BigInteger> collatzStore;

  public ConcurrentCollatzDAOImpl() {
    this.collatzStore = new ConcurrentHashMap<BigInteger, BigInteger>();
  }

  @Override
  public Optional<Map<BigInteger, BigInteger>> get() {
    return Optional.of((Map) new ConcurrentHashMap(collatzStore));
  }

  @Override
  public BigInteger get(BigInteger startTerm) {
    return collatzStore.get(startTerm);
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
  public void save(BigInteger startTerm, BigInteger value) {
    collatzStore.put(startTerm, value);
  }
}
