package collatz.service;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;

public interface Service {

  Map<BigInteger, BigInteger> getAllCollatz();

  Optional<BigInteger> getCollatz(BigInteger startTerm);

  Optional<BigInteger> calcCollatzAsync(BigInteger startTerm);

  boolean deleteCollatz(BigInteger startTerm);

  void deleteAllCollatz();
}


