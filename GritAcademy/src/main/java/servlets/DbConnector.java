package servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/";
    private static final String USERNAME = "<root>";
    private static final String PASSWORD = "<21032021>";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}
