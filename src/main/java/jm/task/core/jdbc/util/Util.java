package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public static Connection getConnect (String urls, String usernames, String passwords) {
        String url = urls;
        String username = usernames;
        String password = passwords;

        try (Connection conn = DriverManager.getConnection(url, username, password)){
            return conn;
        } catch (SQLException e) {
            System.out.println("Соединение не удалось - SQLException - " + e);
        }
        return null;
    }

    public static Connection getConnect () {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/userdb", "root", "Jx321540321540")){
            return conn;
        } catch (SQLException e) {
            System.out.println("Соединение не удалось - SQLException - " + e);
        }
        return null;
    }
}
