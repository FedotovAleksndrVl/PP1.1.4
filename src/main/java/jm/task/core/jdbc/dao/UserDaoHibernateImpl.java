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
    //Session sessio = sessionFactory.cl;

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        final String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            System.out.println("Вот в метоже createUsersTable возникла ошибка!!!");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        final String sql = "DROP TABLE IF EXISTS users";

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            System.out.println("Вот в метоже dropUsersTable возникла ошибка!!!");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        final User user = new User(name, lastName, age);

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Вот в метоже saveUser возникла ошибка!!!");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.flush();
        } catch (Exception e) {
            System.out.println("Вот в метоже removeUserById возникла ошибка!!!");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;
        try (Session session = sessionFactory.openSession()) {
            users = session.createQuery("From User").list();
            return users;
        } catch (Exception e) {
            System.out.println("Вот в метоже getAllUsers возникла ошибка!!!");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        final String sql = "DELETE FROM User";

        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            session.createQuery(sql).executeUpdate();
            session.flush();
        } catch (Exception e) {
            System.out.println("Вот в метоже cleanUsersTable возникла ошибка!!!");
            e.printStackTrace();
        }
    }
}
