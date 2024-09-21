package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.*;
import java.util.List;

import static org.hibernate.resource.transaction.spi.TransactionStatus.*;


public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        final String sql = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.getStatus() == ACTIVE || tx.getStatus() == MARKED_ROLLBACK) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        final String sql = "DROP TABLE IF EXISTS users";

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            tx.commit();
        } catch (Exception e) {
            if (tx.getStatus() == ACTIVE || tx.getStatus() == MARKED_ROLLBACK) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        final User user = new User(name, lastName, age);

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        } catch (Exception e) {
            if (tx.getStatus() == ACTIVE || tx.getStatus() == MARKED_ROLLBACK) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {

        Transaction tx = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tx = session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx.getStatus() == ACTIVE || tx.getStatus() == MARKED_ROLLBACK) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users;

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            users = session.createQuery("From User").list();
            tx.commit();
            return users;
        } catch (Exception e) {
            if (tx.getStatus() == ACTIVE || tx.getStatus() == MARKED_ROLLBACK) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        final String sql = "DELETE FROM User";

        Transaction tx = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            tx = session.beginTransaction();
            session.createQuery(sql).executeUpdate();
            session.flush();
            tx.commit();
        } catch (Exception e) {
            if (tx.getStatus() == ACTIVE || tx.getStatus() == MARKED_ROLLBACK) {
                tx.rollback();
            }
            e.printStackTrace();
        }
    }
}
