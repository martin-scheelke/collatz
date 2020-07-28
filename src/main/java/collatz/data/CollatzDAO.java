package collatz.data;

import java.math.BigInteger;
import java.util.Map;

public interface CollatzDAO {

  Map<BigInteger, BigInteger> get();

  BigInteger get(BigInteger startTerm);

  boolean delete(BigInteger startTerm);

  void delete();

  void save(BigInteger startTerm, BigInteger value);
}
