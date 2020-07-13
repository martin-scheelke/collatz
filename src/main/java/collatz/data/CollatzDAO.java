package collatz.data;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;

public interface CollatzDAO {

  Map<BigInteger, BigInteger> get();

  BigInteger get(BigInteger startTerm);

  void delete(BigInteger startTerm);

  void delete();

  void save(BigInteger startTerm, BigInteger value);
}
