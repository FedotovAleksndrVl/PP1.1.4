package jm.task.core.jdbc.dao;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public static Connection connect = Util.getConnect();

    public UserDaoJDBCImpl() {

    }

    /*

    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private Byte age;
     */

    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(25), lastname VARCHAR(25), " +
                "age TINYINT);";

        try (PreparedStatement statement = Util.getConnect().prepareStatement(sql)) {
            statement.executeUpdate();
        } catch (SQLException ex){
            System.out.println("Не удалось создать statement - Message - " +  ex.getMessage());
        }
    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
