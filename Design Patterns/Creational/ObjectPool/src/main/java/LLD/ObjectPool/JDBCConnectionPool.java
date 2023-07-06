package LLD.ObjectPool;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionPool extends ObjectPool<Connection> {
    String dsn, usr, pwd;

    JDBCConnectionPool(String driver, String dsn, String usr, String pwd) {
        super();
        this.dsn = dsn;
        this.usr = usr;
        this.pwd = pwd;
        try {
            Class.forName(driver).getConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    Connection create() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dsn, usr, pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    boolean validate(Connection connection) {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    void dead(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
