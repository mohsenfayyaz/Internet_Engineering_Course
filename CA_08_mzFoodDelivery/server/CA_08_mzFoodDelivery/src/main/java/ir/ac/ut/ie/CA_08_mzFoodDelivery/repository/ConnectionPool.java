package ir.ac.ut.ie.CA_08_mzFoodDelivery.repository;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        // remote db
        ds.setUrl("jdbc:mysql://mz-food-mysql:3306/mzFoodDelivery");
        ds.setUsername("test");
        ds.setPassword("123456");
        ds.setMinIdle(1);
        ds.setMaxIdle(2000);
        ds.setMaxOpenPreparedStatements(2000);

    }

    public static Connection getConnection() throws SQLException {
        try {
            return ds.getConnection();
        }catch (Exception e){
            ds.setPassword("123456");
            return ds.getConnection();
        }
    }

    private ConnectionPool() {
    }
}
