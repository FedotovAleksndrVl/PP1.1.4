package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;

import java.sql.Connection;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    //private final Connection connection = Util.getConnect();
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    private final Session session = sessionFactory.openSession();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        //Session session = sessionFactory.openSession();
        session.beginTransaction();

        final String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        try {
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.close();
        }
    }

    @Override
    public void dropUsersTable() {
        session.beginTransaction();

        final String sql = "DROP TABLE IF EXISTS users";

        try {
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Util.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
