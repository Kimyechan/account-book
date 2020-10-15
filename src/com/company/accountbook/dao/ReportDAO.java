package com.company.accountbook.dao;

import com.company.accountbook.dto.AccountBook;
import com.company.accountbook.dto.Report;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {
    AccountBook accountBook;

    public ReportDAO(AccountBook accountBook) {
        this.accountBook = accountBook;
    }

    public void addReport(String content, int price, String memo, LocalDate date){
        Connection con = DBConnection.getConnection();
        String sql = "insert into report value(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, content);
            ps.setInt(2, price);
            ps.setString(3, accountBook.getUsername());
            ps.setString(4, memo);
            ps.setInt(5 , date.getDayOfMonth());
            ps.setInt(6, date.getMonthValue());
            ps.setInt(7, date.getYear());
            ps.executeUpdate();

            con.close();
            ps.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Report> getDayReport(int year, int month, int day) {
        List<Report> reports = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "select * from report where account_book_name=? and year=? and month=? and day=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, accountBook.getUsername());
            ps.setInt(2, year);
            ps.setInt(3, month);
            ps.setInt(4, day);
            rs = ps.executeQuery();

            addReportList(rs, reports);

            con.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reports;
    }

    public List<Report> getWeekReport(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    public List<Report> getMonthReport(int year, int month) {
        List<Report> reports = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "select * from report where account_book_name=? and year=? and month=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, accountBook.getUsername());
            ps.setInt(2, year);
            ps.setInt(3, month);
            rs = ps.executeQuery();

            addReportList(rs, reports);

            con.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reports;
    }

    public List<Report> getYearReport(int year) {
        List<Report> reports = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "select * from report where account_book_name=? and year=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, accountBook.getUsername());
            ps.setInt(2, year);
            rs = ps.executeQuery();

            addReportList(rs, reports);

            con.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reports;
    }

    public void addReportList(ResultSet rs, List<Report> reports) throws SQLException {
        while(rs.next()) {
            String content = rs.getString("content");
            int price = rs.getInt("price");
            String memo = rs.getString("memo");
            int newYear = rs.getInt("year");
            int newMonth = rs.getInt("month");
            int newDay = rs.getInt("day");

            reports.add(new Report(content, price, memo, accountBook.getUsername(), newYear, newMonth, newDay));
        }
    }
}
