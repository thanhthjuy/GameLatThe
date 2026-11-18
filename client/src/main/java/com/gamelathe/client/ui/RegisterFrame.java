
package com.gamelathe.client.ui;

import com.gamelathe.client.service.AuthClient;

import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public RegisterFrame() {
        setTitle("Game Lật Thẻ - Đăng ký");
        setSize(400, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("ĐĂNG KÝ TÀI KHOẢN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 21));
        titleLabel.setBounds(75, 25, 260, 35);
        panel.add(titleLabel);

        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
        usernameLabel.setBounds(40, 85, 130, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(40, 110, 300, 35);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setBounds(40, 155, 130, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(40, 180, 300, 35);
        panel.add(passwordField);

        JLabel confirmLabel = new JLabel("Nhập lại mật khẩu:");
        confirmLabel.setBounds(40, 225, 160, 25);
        panel.add(confirmLabel);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBounds(40, 250, 300, 35);
        panel.add(confirmPasswordField);

        JButton registerButton = new JButton("Đăng ký");
        registerButton.setBounds(40, 310, 140, 35);
        panel.add(registerButton);

        JButton backButton = new JButton("Quay lại");
        backButton.setBounds(200, 310, 140, 35);
        panel.add(backButton);

        registerButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (username.isEmpty() || password.isEmpty()
                    || confirmPassword.isEmpty()) {

                JOptionPane.showMessageDialog(this,
                        "Vui lòng nhập đầy đủ thông tin!");

            } else if (!password.equals(confirmPassword)) {

                JOptionPane.showMessageDialog(this,
                        "Mật khẩu nhập lại không khớp!");

            } else {

                AuthClient authClient = new AuthClient();

                String message = authClient.register(username, password);

                JOptionPane.showMessageDialog(this, message);

                if (message.equals("Đăng ký thành công!")) {
                    LoginFrame loginFrame = new LoginFrame();
                    loginFrame.setVisible(true);
                    dispose();
                }
            }
        });

        backButton.addActionListener(e -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            dispose();
        });

        add(panel);
    }
}
