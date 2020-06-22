package collatz.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.function.Function;
import javax.sql.DataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBJooqWrapper {

  static Logger log = LoggerFactory.getLogger(DBJooqWrapper.class);

  private final Settings settings = new Settings();

  private final DataSource dataSource;
  private final SQLDialect dialect;

  public DBJooqWrapper(final DataSource ds) {
    this.dataSource = ds;
    this.dialect = SQLDialect.POSTGRES;
  }

  public DBJooqWrapper() {
    this.dataSource = DatasourceFactory.getInstance().getDataSource();
    this.dialect = SQLDialect.POSTGRES;
  }

  public <T> T execute(final Function<DSLContext, T> fun) {

    if (null == dataSource) {
      throw new RuntimeException("Datasource not initialised.");
    }
    
    T result = null;
    Connection connection = null;
    RuntimeException userException = null;

    try {
      connection = dataSource.getConnection();
      connection.setAutoCommit(false);
      final DSLContext ctx = DSL.using(connection, dialect, settings);

      result = fun.apply(ctx); //Code and collatz.database calls executed here

    } catch (final Exception e) { 
      final Throwable cause = e.getCause();
      if (null != cause) {
        userException = new RuntimeException(cause);
      }
    }

    try {
      if (null == userException) {
        connection.commit();
      } else {
        if (!connection.isClosed()) {
          connection.rollback();
        }
        log.warn("Could not execute TX", userException);
      }
    } catch (SQLException ex) {
      throw new RuntimeException(ex);
    } finally {
      try {
        if (null != connection && !connection.isClosed()) {// Release the connection back to the pool
          connection.close();
        }
      } catch (final SQLException se2) {
        throw new RuntimeException(se2);
      }
    }
    return result;
  }
}
