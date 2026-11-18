
package com.gamelathe.server.service;

import com.gamelathe.server.model.User;
import com.gamelathe.server.repository.UserRepository;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;

public class AuthService {

    private final UserRepository userRepository;

    public AuthService() {
        this.userRepository = new UserRepository();
    }

    // Đăng ký tài khoản
    public String register(String username, String password) throws SQLException {

        if (username == null || username.trim().isEmpty()) {
            return "Tên đăng nhập không được để trống!";
        }

        if (password == null || password.length() < 6) {
            return "Mật khẩu phải có ít nhất 6 ký tự!";
        }

        username = username.trim();

        if (userRepository.existsByUsername(username)) {
            return "Tên đăng nhập đã tồn tại!";
        }

        String passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordHash);

        if (userRepository.create(user)) {
            return "Đăng ký thành công!";
        }

        return "Đăng ký thất bại!";
    }

    // Đăng nhập tài khoản
    public User login(String username, String password) throws SQLException {

        if (username == null || password == null) {
            return null;
        }

        User user = userRepository.findByUsername(username.trim());

        if (user == null) {
            return null;
        }

        if (BCrypt.checkpw(password, user.getPasswordHash())) {
            return user;
        }

        return null;
    }
}
