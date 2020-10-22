package com.company.accountbook.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static String server; // MySQL 서버 주소
    private static String database;  // MySQL DATABASE 이름
    private static String user_name;  //  MySQL 서버 아이디
    private static String password; // MySQL 서버 비밀번호

    public static void setDBServer() {
        try {
            FileReader fileReader = new FileReader("DBServerInfo");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            server = bufferedReader.readLine();
            database = bufferedReader.readLine();
            user_name = bufferedReader.readLine();
            password = bufferedReader.readLine();
<<<<<<< HEAD
=======

>>>>>>> yechan
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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
        } catch (SQLException e) {
            System.err.println("con 오류:" + e.getMessage());
            e.printStackTrace();
        }

        return con;
    }
}
