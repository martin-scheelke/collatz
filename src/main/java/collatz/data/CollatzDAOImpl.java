package collatz.data;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class CollatzDAOImpl implements CollatzDAO {

  HashMap<BigInteger, BigInteger> collatzStore;

  public CollatzDAOImpl() {
    this.collatzStore = new HashMap<BigInteger, BigInteger>();
  }

  @Override
  public Map<BigInteger, BigInteger> get() {
    return (Map) new HashMap(collatzStore);
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
  public BigInteger get(BigInteger startTerm) {
    return collatzStore.get(startTerm);
  }

  @Override
  public void save(BigInteger startTerm, BigInteger value) {
    collatzStore.put(startTerm, value);
  }
}
