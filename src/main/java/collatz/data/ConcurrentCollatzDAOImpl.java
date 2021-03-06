package collatz.data;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCollatzDAOImpl implements CollatzDAO {

  ConcurrentHashMap<BigInteger, BigInteger> collatzStore;

  public ConcurrentCollatzDAOImpl() {
    this.collatzStore = new ConcurrentHashMap<BigInteger, BigInteger>();
  }

  @Override
  public Map<BigInteger, BigInteger> get() {
    return (Map) new ConcurrentHashMap(collatzStore);
  }

  @Override
  public BigInteger get(BigInteger startTerm) {
    return collatzStore.get(startTerm);
  }

  @Override
  public boolean delete(BigInteger startTerm) {
    if (!collatzStore.containsKey(startTerm)) {
      return false;
    } else {
      collatzStore.remove(startTerm);
      return true;
    }
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
