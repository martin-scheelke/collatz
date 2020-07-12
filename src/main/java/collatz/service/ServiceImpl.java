package collatz.service;

import collatz.data.CollatzDAO;
import collatz.task.RunnableCollatzTask;
import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Service for Collatz series operations
 */
public class ServiceImpl implements Service {

  ThreadPoolExecutor executor;
  CollatzDAO collatzDAO;

  public ServiceImpl(CollatzDAO collatzDAO) {
    this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
  }

  /**
   * Asynchronous calculation of Collatz series by submitting to {@link ThreadPoolExecutor}.
   */
  @Override
  public Optional<BigInteger> calcCollatzAsync(BigInteger startTerm) {
    Optional<Map<BigInteger, BigInteger>> collatzResults = collatzDAO.get();
    if (collatzResults.isEmpty() || !collatzResults.get().containsKey(startTerm)) {
      RunnableCollatzTask task = new RunnableCollatzTask(startTerm, collatzDAO);
      executor.submit(task);
      return Optional.empty();
    } else {
      return Optional.of(collatzResults.get().get(startTerm));
    }
  }

  @Override
  public Map<BigInteger, BigInteger> getAllCollatz() {
    return collatzDAO.get().orElse(null);
  }

  @Override
  public Optional<BigInteger> getCollatz(BigInteger startTerm) {
    Optional<Map<BigInteger, BigInteger>> collatzResults = collatzDAO.get();
    if (collatzResults.isPresent() && collatzResults.get().containsKey(startTerm)) {
      return Optional.empty();
    } else {
      return Optional.of(collatzResults.get().get(startTerm));
    }
  }

  @Override
  public void deleteAllCollatz() {
    collatzDAO.delete();
  }

  @Override
  public boolean deleteCollatz(BigInteger startTerm) {
    Optional<Map<BigInteger, BigInteger>> collatzResults = collatzDAO.get();
    if (collatzResults.isPresent() && !collatzResults.get().containsKey(startTerm)) {
      return false;
    } else {
      collatzResults.get().remove(startTerm);
      return true;
    }
  }
}
