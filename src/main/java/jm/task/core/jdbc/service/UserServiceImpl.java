package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao instanceDao = new UserDaoJDBCImpl();

    @Override
    public void createUsersTable() {
        instanceDao.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        instanceDao.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        instanceDao.saveUser(name,lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        instanceDao.removeUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return instanceDao.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        instanceDao.cleanUsersTable();
    }
}
