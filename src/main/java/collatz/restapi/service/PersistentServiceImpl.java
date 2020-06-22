//package collatz.restapi.service;
//
//import static collatz.db.Tables.COLLATZ;
//
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.Map;
//import java.util.Optional;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ThreadPoolExecutor;
//import collatz.task.RunnablePersistentCollatzTask;
//
///**
// * Service for Collatz series operations with persistent result storage and recall.
// * <p>
// *   Persistence is transactional and uses the jOOQ DSL for SQL expression.
// */
//public class PersistentServiceImpl implements Service {
//
//  ThreadPoolExecutor executor;
//
//  public PersistentServiceImpl() {
//    this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
//  }
//
//  /**
//   * Asynchronous calculation of Collatz series by submitting to {@link ThreadPoolExecutor}.
//   */
//  @Override
//  public Optional<BigInteger> calcCollatzAsync(BigInteger startTerm) {
//    return dbJooqWrapper.execute(tx -> {
//      final var record = tx.select(COLLATZ.STEPS)
//        .from(COLLATZ)
//        .where(COLLATZ.START_TERM.eq(new BigDecimal(startTerm)))
//        .fetchOptional();
//
//      if (record.isEmpty()) {
//        RunnablePersistentCollatzTask task = new RunnablePersistentCollatzTask(startTerm);
//        executor.submit(task);
//        return Optional.empty();
//      } else {
//        return Optional.of(record.get().value1().toBigInteger());
//      }
//    });
//  }
//
//  @Override
//  public Map<BigInteger, BigInteger> getAllCollatz() {
//    return dbJooqWrapper.execute(tx -> {
//      return tx.selectFrom(COLLATZ)
//        .fetch()
//        .intoMap(COLLATZ.START_TERM.cast(BigInteger.class), COLLATZ.STEPS.cast(BigInteger.class));
//    });
//  }
//
//  @Override
//  public Optional<BigInteger> getCollatz(BigInteger startTerm) {
//    return dbJooqWrapper.execute(tx -> {
//      final var record = tx.select(COLLATZ.STEPS)
//        .from(COLLATZ)
//        .where(COLLATZ.START_TERM.eq(new BigDecimal(startTerm)))
//        .fetchOptional();
//
//      if (record.isEmpty()) {
//        return Optional.empty();
//      } else {
//        return Optional.of(record.get().value1().toBigInteger());
//      }
//    });
//  }
//
//  @Override
//  public void deleteAllCollatz() {
//    dbJooqWrapper.execute(tx -> {
//      tx.deleteFrom(COLLATZ)
//        .execute();
//      return 0;
//    });
//  }
//
//  @Override
//  public boolean deleteCollatz(BigInteger startTerm) {
//    return dbJooqWrapper.execute(tx -> {
//      final var deletedNumber = tx.deleteFrom(COLLATZ)
//        .where(COLLATZ.START_TERM.eq(new BigDecimal(startTerm)))
//        .execute();
//
//      if (deletedNumber < 1) {
//        return false;
//      } else {
//        return true;
//      }
//    });
//  }
//}
