package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        final UserService userService = new UserServiceImpl();
        ArrayList<User> user = new ArrayList<>();
        user.add(new User("Александр", "Александров", (byte) 25));
        user.add(new User("Инесса", "Инессова", (byte) 25));
        user.add(new User("Олег", "Олегархов", (byte) 27));
        user.add(new User("Юлия", "Юлова", (byte) 27));

        userService.createUsersTable();

        for (User el : user) {
            userService.saveUser(el.getName(), el.getLastName(), el.getAge());
            System.out.printf("User с именем — %s добавлен в базу данны \n", el.getName());
        }

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
