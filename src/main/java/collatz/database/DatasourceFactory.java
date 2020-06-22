package collatz.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.util.Properties;
import collatz.Util;

public class DatasourceFactory {

  private static final DatasourceFactory INSTANCE = new DatasourceFactory();
  private static final Object mutex = new Object();

  private volatile HikariDataSource dataSource = null;

  private DatasourceFactory() {
  }

  public static DatasourceFactory getInstance() {
    return INSTANCE;
  }

  public HikariDataSource getDataSource() {

    HikariDataSource result = dataSource;
    if ((result == null) || result.isClosed()) {
      synchronized (mutex) {
        try {
          result = dataSource;
          if ((result == null) || result.isClosed()) {

            Properties props = new Properties();

            props.setProperty("databaseName", "postgres");
            props.setProperty("serverName", "127.0.0.1");

            HikariConfig config = new HikariConfig();
            config.setDataSourceProperties(props);

            config.setDataSourceClassName("org.postgresql.ds.PGSimpleDataSource");

            config.setUsername(Util.getProp("postgres.user"));
            config.setPassword(Util.getProp("postgres.pass"));
            config.setAutoCommit(false);
            config.setConnectionInitSql("SET TIME ZONE 'UTC'");

            dataSource = result = new HikariDataSource(config);
          }
        } catch (IOException e) {
          throw new RuntimeException("Could not read from properties file.", e);
        }
      }
    }
    return result;
  }
}
