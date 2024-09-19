package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    //public static Connection connect = Util.getConnect();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS USERS(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "names VARCHAR(25), lastnames VARCHAR(25), " +
                "ages TINYINT)";

        try (Statement statement = Util.getConnect().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException ex){
            System.out.println("Не удалось создать таблицу - Message - " +  ex.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS USERS";

        try (Statement statement = Util.getConnect().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException ex){
            System.out.println("Не удалось удалить таблицу - Message - " +  ex.getMessage());
        }
    }

    @Override
    public void saveUser(String name, String lastname, byte age) {
        String sql = "INSERT INTO USERS(names, lastnames, ages) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement statement = Util.getConnect().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastname);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println("Не удалось записать в таблицу - Message - " +  ex.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "DELETE FROM USERS WHERE Id = " + id;

        try (Statement statement = Util.getConnect().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException ex){
            System.out.println("Не удалось удалить из таблицы - Message - " +  ex.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {

        ArrayList<User> list = new ArrayList<>();

        try (Statement statement = Util.getConnect().createStatement()) {
            ResultSet results = statement.executeQuery("SELECT * FROM USERS");

            while (results.next()) {
                User users = new User();
                users.setId(results.getLong(1));
                users.setName(results.getString(2));
                users.setLastName(results.getString(3));
                users.setAge(results.getByte(4));
                list.add(users);
            }
            return list;
        } catch (SQLException ex){
            System.out.println("Ошибка при чтении из таблицы - Message - " +  ex.getMessage());
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {

        String sql = "TRUNCATE TABLE USERS";
        try (Statement statement = Util.getConnect().createStatement()) {
            ResultSet results = statement.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'db' AND TABLE_NAME = 'users'");
            if (results.next()) {
                statement.executeUpdate(sql);
            }
        } catch (SQLException ex){
            System.out.println("Не удалось очистить таблицу - Message - " +  ex.getMessage());
        }
    }
}
