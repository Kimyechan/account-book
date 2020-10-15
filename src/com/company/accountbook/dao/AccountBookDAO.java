package com.company.accountbook.dao;

import com.company.accountbook.dto.Report;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class AccountBookDAO {

    public static void main(String[] args) {
        Connection con = null;

        String server = "localhost"; // MySQL 서버 주소
        String database = ""; // MySQL DATABASE 이름
        String user_name = "root"; //  MySQL 서버 아이디
        String password = "12341234"; // MySQL 서버 비밀번호

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

        // 3.해제
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
        }
    }

    public List<Report> getDayReport(int year, int month, int day) {
        // TODO: make sql
        String sql = "select * from account_book where day == && ";
        return null;
    }

    public List<Report> getWeekReport(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    public List<Report> getMonthReport(int year, int month) {
        return null;
    }

    public List<Report> getYearReport(int year) {
        return null;
    }
}
