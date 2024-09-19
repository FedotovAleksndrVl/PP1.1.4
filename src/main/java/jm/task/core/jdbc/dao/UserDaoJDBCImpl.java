package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS USERS(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "names VARCHAR(25), lastnames VARCHAR(25), " +
                "ages TINYINT)";

        try (PreparedStatement statement = Util.getConnect().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println("Не удалось создать таблицу - Message - " +  ex.getMessage());
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS USERS";

        try (PreparedStatement statement = Util.getConnect().prepareStatement(sql)) {
            statement.executeUpdate();
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

        try (PreparedStatement statement = Util.getConnect().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println("Не удалось удалить из таблицы - Message - " +  ex.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();

        try (PreparedStatement statement = Util.getConnect().prepareStatement("SELECT * FROM USERS")) {
            ResultSet results = statement.executeQuery();

            while (results.next()) {
                User users = new User();
                users.setId(results.getLong("id"));
                users.setName(results.getString("names"));
                users.setLastName(results.getString("lastnames"));
                users.setAge(results.getByte("ages"));
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

        try (PreparedStatement statement = Util.getConnect().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println("Не удалось очистить таблицу - Message - " +  ex.getMessage());
            ex.printStackTrace();
        }
    }
}
