package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao instanceDAO = new UserDaoJDBCImpl();

    public void createUsersTable() {
        instanceDAO.createUsersTable();
    }

    @Override
    public String toString() {
        return "UserServiceImpl{" +
                "instanceDAO=" + instanceDAO +
                '}';
    }

    public void dropUsersTable() {
        instanceDAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        instanceDAO.saveUser(name,lastName, age);
    }

    public void removeUserById(long id) {
        instanceDAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return instanceDAO.getAllUsers();
    }

    public void cleanUsersTable() {
        instanceDAO.createUsersTable();
    }
}
