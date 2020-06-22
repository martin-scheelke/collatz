//package collatz.task;
//
//import static collatz.CollatzTailKt.collatzTailRecursive;
//import static collatz.db.Tables.COLLATZ;
//
//import collatz.database.DBJooqWrapper;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//
///**
// * Thread for calculating Collatz Series asynchronously and persisting
// */
//public class RunnablePersistentCollatzTask
//  implements Runnable {
//
//  private BigInteger startTerm;
//  DBJooqWrapper dbJooqWrapper;
//
//  public RunnablePersistentCollatzTask(BigInteger startTerm) {
//    dbJooqWrapper = new DBJooqWrapper();
//    this.startTerm = startTerm;
//  }
//
//  /**
//   * Calculate Collatz and persist. These two steps are wrapped in a transaction.
//   */
//  public void run() {
//    dbJooqWrapper.execute(tx -> {
//      BigInteger steps = collatzTailRecursive(startTerm);
//      tx.insertInto(COLLATZ)
//        .set(COLLATZ.START_TERM, new BigDecimal(startTerm))
//        .set(COLLATZ.STEPS, new BigDecimal(steps))
//        .execute();
//      return steps;
//    });
//  }
//}
