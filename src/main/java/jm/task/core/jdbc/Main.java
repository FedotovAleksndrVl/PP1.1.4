package jm.task.core.jdbc;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        final UserService userService = new UserServiceImpl();
        final ArrayList<User> user = new ArrayList<>();
        user.add(new User("Александр", "Александров", (byte) 25));
        user.add(new User("Инесса", "Инессова", (byte) 25));
        user.add(new User("Олег", "Олегархов", (byte) 27));
        user.add(new User("Юлия", "Юлова", (byte) 27));

        userService.createUsersTable();

        for (User el : user) {
            userService.saveUser(el.getName(), el.getLastName(), el.getAge());
            System.out.printf("User с именем — %s добавлен в базу данны \n", el.getName());
        }

        for (User el: userService.getAllUsers()){
            System.out.println(el);
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}
