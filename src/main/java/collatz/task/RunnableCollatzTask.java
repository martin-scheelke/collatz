package collatz.task;

import static collatz.CollatzTailKt.collatzTailRecursive;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentMap;

/**
 * Thread for calculating Collatz Series asynchronously
 */
public class RunnableCollatzTask
  implements Runnable {

  private BigInteger startTerm;
  private ConcurrentMap<BigInteger, BigInteger> collatzStore;

  public RunnableCollatzTask(BigInteger startTerm, ConcurrentMap<BigInteger, BigInteger> collatzStore) {
    this.collatzStore = collatzStore;
    this.startTerm = startTerm;
  }

  public BigInteger getStartTerm() {
    return startTerm;
  }

  public void run() {
    BigInteger steps = collatzTailRecursive(startTerm);
    collatzStore.put(startTerm, steps);
  }
}