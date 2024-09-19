package jm.task.core.jdbc.util;
import java.sql.*;

public class Util {
    private final static String URL = "jdbc:mysql://localhost:3306/DB";
    private final static String USERNAME = "admin";
    private final static String PASSWORDS = "admin";

    public static Connection getConnect () {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORDS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
