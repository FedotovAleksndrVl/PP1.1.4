package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Driver;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        /*Class<Driver> driverClass = Driver.class;
        try (var connection = Util.getConnect()){
            System.out.println(connection.getTransactionIsolation());
        }*/
        //UserDao dao = new UserDaoJDBCImpl();
        //dao.createUsersTable();
        //dao.cleanUsersTable();
        /*
        dao.saveUser("Автор", "Автор", (byte) 24);
        dao.removeUserById(6);
        for (User el : dao.getAllUsers()) {
            System.out.print(el.getName() + " " + el.getAge() + "\n");
        }
        dao.dropUsersTable();
        dao.cleanUsersTable();
        //System.out.println(UserDaoJDBCImpl.connect.getTransactionIsolation());
*/
        //UserDao userService = new UserDaoJDBCImpl();
        final UserService userService = new UserServiceImpl();

    }
}
