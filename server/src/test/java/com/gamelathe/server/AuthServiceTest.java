
package com.gamelathe.server;

import com.gamelathe.server.model.User;
import com.gamelathe.server.service.AuthService;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

    @Test
    void testRegisterAndLogin() throws Exception {

        AuthService authService = new AuthService();

        // Tạo username riêng để tránh trùng tài khoản
        String username = "test" + System.currentTimeMillis();
        String password = "123456";

        // Kiểm tra đăng ký
        String registerResult = authService.register(username, password);

        assertEquals("Đăng ký thành công!", registerResult);

        // Kiểm tra đăng nhập đúng mật khẩu
        User user = authService.login(username, password);

        assertNotNull(user);
        assertEquals(username, user.getUsername());

        // Kiểm tra đăng nhập sai mật khẩu
        User wrongLogin = authService.login(username, "sai_mat_khau");

        assertNull(wrongLogin);
    }
}
