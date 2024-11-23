package org.example.dao;


import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.ds.*;

public class DataSourceFactory {

    private static final Logger LOGGER = Logger.getLogger(DataSourceFactory.class.getName());
    private final DataSource dataSource;

    private DataSourceFactory() {
        PGSimpleDataSource pgDataSource = new PGSimpleDataSource();
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("database.properties")).getPath();

        try (InputStream input = new FileInputStream(rootPath)) {
            Properties prop = new Properties();
            prop.load(input);

            pgDataSource.setDatabaseName(prop.getProperty("database"));
            pgDataSource.setServerNames(new String[]{prop.getProperty("serverName")});
            pgDataSource.setPortNumbers(new int[]{Integer.parseInt(prop.getProperty("port"))});
            pgDataSource.setUser(prop.getProperty("user"));
            pgDataSource.setPassword(prop.getProperty("password"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to load database.properties", e);
        }

        this.dataSource = pgDataSource;
    }

    public static Connection getConnection() throws SQLException {
        return SingletonHelper.INSTANCE.dataSource.getConnection();
    }

    private static class SingletonHelper {
        private static final DataSourceFactory INSTANCE = new DataSourceFactory();
    }
}
