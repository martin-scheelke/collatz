package collatz.service;

import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import collatz.task.RunnableCollatzTask;

/**
 * Service for Collatz series operations
 */
public class ServiceImpl implements Service {

  ConcurrentHashMap<BigInteger, BigInteger> collatzStore;
  ThreadPoolExecutor executor;

  public ServiceImpl() {
    this.collatzStore = new ConcurrentHashMap<>();
    this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
  }

  /**
   * Asynchronous calculation of Collatz series by submitting to {@link ThreadPoolExecutor}.
   */
  @Override
  public Optional<BigInteger> calcCollatzAsync(BigInteger startTerm) {
    if (!collatzStore.containsKey(startTerm)) {
      RunnableCollatzTask task = new RunnableCollatzTask(startTerm, collatzStore);
      executor.submit(task);
      return Optional.empty();
    } else {
      return Optional.of(collatzStore.get(startTerm));
    }
  }

  @Override
  public Map<BigInteger, BigInteger> getAllCollatz() {
    return collatzStore;
  }

  @Override
  public Optional<BigInteger> getCollatz(BigInteger startTerm) {
    if (!collatzStore.containsKey(startTerm)) {
      return Optional.empty();
    } else {
      return Optional.of(collatzStore.get(startTerm));
    }
  }

  @Override
  public void deleteAllCollatz() {
    collatzStore.clear();
  }

  @Override
  public boolean deleteCollatz(BigInteger startTerm) {
    if (!collatzStore.containsKey(startTerm)) {
      return false;
    } else {
      collatzStore.remove(startTerm);
      return true;
    }
  }
}
