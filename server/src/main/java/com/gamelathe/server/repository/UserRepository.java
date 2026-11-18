
package com.gamelathe.server.repository;

import com.gamelathe.server.config.DatabaseConnection;
import com.gamelathe.server.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    // Kiểm tra username đã tồn tại chưa
    public boolean existsByUsername(String username) throws SQLException {
        String sql = "SELECT id FROM users WHERE username = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        }
    }

    // Thêm tài khoản mới
    public boolean create(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password_hash) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPasswordHash());

            return statement.executeUpdate() > 0;
        }
    }

    // Tìm tài khoản theo username
    public User findByUsername(String username) throws SQLException {
        String sql = """
                SELECT id, username, password_hash, created_at
                FROM users
                WHERE username = ?
                """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getLong("id"),
                            resultSet.getString("username"),
                            resultSet.getString("password_hash"),
                            resultSet.getTimestamp("created_at").toLocalDateTime()
                    );
                }
            }
        }

        return null;
    }
}
