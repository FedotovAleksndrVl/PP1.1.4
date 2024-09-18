package jm.task.core.jdbc.util;

import java.sql.*;

public final class Util {

    public static Connection getConnect () {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/DB", "admin", "admin");
        } catch (SQLException e) {
            System.out.println("Соединение не удалось - SQLException - " + e);
        }
        return null;
    }

    public static Connection getConnect (String url, String username, String password) {
        final String URL = url;
        final String USERNAME = username;
        final String PASSWORDS = password;

        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORDS);
        } catch (SQLException e) {
            System.out.println("Соединение не удалось - SQLException - " + e);
        }
        return null;
    }
}
