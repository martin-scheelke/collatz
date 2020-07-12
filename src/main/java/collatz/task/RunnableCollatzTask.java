package collatz.task;

import static collatz.CollatzTailKt.collatzTailRecursive;

import collatz.data.CollatzDAO;
import java.math.BigInteger;

/**
 * Thread for calculating Collatz Series asynchronously
 */
public class RunnableCollatzTask
    implements Runnable {

  CollatzDAO collatzDAO;
  private BigInteger startTerm;

  public RunnableCollatzTask(BigInteger startTerm,
      CollatzDAO collatzDAO) {
    this.collatzDAO = collatzDAO;
    this.startTerm = startTerm;
  }

  public BigInteger getStartTerm() {
    return startTerm;
  }

  public void run() {
    BigInteger steps = collatzTailRecursive(startTerm);
    collatzDAO.save(startTerm, steps);
  }
}