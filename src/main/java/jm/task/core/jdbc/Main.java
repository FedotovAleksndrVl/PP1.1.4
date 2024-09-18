package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Driver;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*Class<Driver> driverClass = Driver.class;
        try (var connection = Util.getConnect()){
            System.out.println(connection.getTransactionIsolation());
        }*/
        UserDao dao = new UserDaoJDBCImpl();
        dao.createUsersTable();
        System.out.println(UserDaoJDBCImpl.connect.getTransactionIsolation());

    }
}
