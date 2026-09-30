package ma.youcode.clinique.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DBConnection {

    private static final DataSource dataSource ;

    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(ModelDB.URL);
        config.setUsername(ModelDB.USER);
        config.setPassword(ModelDB.PASSWORD);
        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}