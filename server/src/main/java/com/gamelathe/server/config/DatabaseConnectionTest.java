
package com.gamelathe.server.config;

import java.sql.Connection;

public class DatabaseConnectionTest {

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            System.out.println("KET NOI MYSQL THANH CONG!");
        } catch (Exception e) {
            System.out.println("KET NOI MYSQL THAT BAI!");
            e.printStackTrace();
        }
    }
}