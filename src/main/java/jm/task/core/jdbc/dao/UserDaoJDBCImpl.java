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
       /* try (var statement = connect.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastname VARCHAR(50), age TINYINT);");
        } catch (SQLException ex) {
            System.out.println("Не удалось создать statement - Message - " +  ex.getMessage());
        }*/

        String sql = "CREATE TABLE IF NOT EXISTS users(" +
                "id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                "name VARCHAR(50), lastname VARCHAR(50), " +
                "age TINYINT);";

        try (PreparedStatement statement = connect.prepareStatement(sql)) {
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
