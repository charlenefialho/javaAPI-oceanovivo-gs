package com.oceanovivo.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class OracleDBTest {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//oracle.fiap.com.br:1521/orcl";
        String username = "rm552252";
        String password = "200305";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Conexão bem-sucedida!");
            } else {
                System.out.println("Falha na conexão!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

