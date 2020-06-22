package collatz.restapi.service;

import collatz.database.DBJooqWrapper;
import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;

public interface Service {

  DBJooqWrapper dbJooqWrapper = new DBJooqWrapper();
  
  Map<BigInteger, BigInteger> getAllCollatz();

  Optional<BigInteger> getCollatz(BigInteger startTerm);

  Optional<BigInteger> calcCollatzAsync(BigInteger startTerm);

  boolean deleteCollatz(BigInteger startTerm);

  void deleteAllCollatz();
}


