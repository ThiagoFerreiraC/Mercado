package br.com.sinqia;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class MySQLConnection {
    private static Connection mySQLConnection;

    private MySQLConnection() {
    }

    public static Connection getInstance() {
        if (mySQLConnection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/Mercado";
                String user = "root";
                String password = "D0CT0R!wh0@9";
                mySQLConnection = DriverManager.getConnection(url, user, password);
                return mySQLConnection;
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            return mySQLConnection;
        }
    }
}
