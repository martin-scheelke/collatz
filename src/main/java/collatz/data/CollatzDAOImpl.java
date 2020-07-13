package collatz.data;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CollatzDAOImpl implements CollatzDAO {

  HashMap<BigInteger, BigInteger> collatzStore;

  public CollatzDAOImpl() {
    this.collatzStore = new HashMap<BigInteger, BigInteger>();
  }

  @Override
  public Map<BigInteger, BigInteger> get() {
    return (Map) new ConcurrentHashMap(collatzStore);
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
    return collatzStore.get(startTerm);
  }

  @Override
  public void save(BigInteger startTerm, BigInteger value) {
    collatzStore.put(startTerm, value);
  }
}
