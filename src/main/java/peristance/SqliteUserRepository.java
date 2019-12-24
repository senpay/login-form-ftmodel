package peristance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import business.User;

/**
 * Created by Alexander Pushkarev on 24.12.19.
 */
public class SqliteUserRepository implements IUserRepository {

    String url = "jdbc:sqlite:sqlite.db";

    public SqliteUserRepository() {
        String sql = "CREATE TABLE IF NOT EXISTS users (id integer PRIMARY KEY, name text NOT NULL);";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public synchronized void saveUser(User userToSave) {
        final String sql = "INSERT INTO users(name) VALUES(?)";

        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userToSave.getUserLogInName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public synchronized List<User> getUsers() {
        final String sql = "SELECT * FROM users;";
        List<User> users = new ArrayList<>();
        try (Connection conn = getConnection(); Statement stmt  = conn.createStatement()) {
            final ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                final User user = new User();
                user.setUserLogInName(resultSet.getString("name"));
                users.add(user);
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return users;
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
