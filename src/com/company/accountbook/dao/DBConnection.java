package com.company.accountbook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String server = "localhost"; // MySQL 서버 주소
    private static final String database = "accountbook"; // MySQL DATABASE 이름
    private static final String user_name = "root"; //  MySQL 서버 아이디
    private static final String password = "12341234"; // MySQL 서버 비밀번호

    public static Connection getConnection() {
        Connection con = null;

        // 1.드라이버 로딩
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println(" !! <JDBC 오류> Driver load 오류: " + e.getMessage());
            e.printStackTrace();
        }

        // 2.연결
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + server + "/" + database + "?useSSL=false", user_name, password);
            System.out.println("정상적으로 연결되었습니다.");
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }
}
