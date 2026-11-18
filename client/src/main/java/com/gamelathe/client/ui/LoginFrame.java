
package com.gamelathe.client.ui;

import com.gamelathe.client.service.AuthClient;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Game Lật Thẻ - Đăng nhập");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("GAME LẬT THẺ");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setBounds(95, 25, 230, 40);
        panel.add(titleLabel);

        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
        usernameLabel.setBounds(40, 95, 130, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(40, 120, 300, 35);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setBounds(40, 165, 130, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(40, 190, 300, 35);
        panel.add(passwordField);

        JButton loginButton = new JButton("Đăng nhập");
        loginButton.setBounds(40, 245, 140, 35);
        panel.add(loginButton);

        JButton registerButton = new JButton("Đăng ký");
        registerButton.setBounds(200, 245, 140, 35);
        panel.add(registerButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Vui lòng nhập đầy đủ tên đăng nhập và mật khẩu!");
            } else {
                AuthClient authClient = new AuthClient();
                String message = authClient.login(username, password);

                JOptionPane.showMessageDialog(this, message);
            }
        });

        registerButton.addActionListener(e -> {
            RegisterFrame registerFrame = new RegisterFrame();
            registerFrame.setVisible(true);
        });

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame frame = new LoginFrame();
            frame.setVisible(true);
        });
    }
}
