package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection connection = Util.getConnect();

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() {
        final String sql = "CREATE TABLE IF NOT EXISTS USERS(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "names VARCHAR(25), lastnames VARCHAR(25), " +
                "ages TINYINT)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        final String sql = "DROP TABLE IF EXISTS USERS";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastname, byte age) {
        final String sql = "INSERT INTO USERS(names, lastnames, ages) " +
                "VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastname);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        final String sql = "DELETE FROM USERS WHERE Id = " + id;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM USERS")) {
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
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        final String sql = "TRUNCATE TABLE USERS";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
