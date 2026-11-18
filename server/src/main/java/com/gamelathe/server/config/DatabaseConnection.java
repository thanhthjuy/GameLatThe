package com.gamelathe.server.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/game_lat_the?useSSL=false&serverTimezone=Asia/Ho_Chi_Minh&characterEncoding=UTF-8";

    private static final String USER = "root";

    private static final String PASSWORD = "Th@1234567";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
