package com.company.accountbook.dao;

import com.company.accountbook.dto.Report;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {
    public ReportDAO() {
        DBConnection.setDBServer();
    }

    public void addReportList(ResultSet rs, List<Report> reports) throws SQLException {
        while(rs.next()) {
            int reportId = rs.getInt("report_id");
            boolean isIncome = rs.getBoolean("is_income");
            String paymentMethod = rs.getString("payment_method");
            String category = rs.getString("category");
            int price = rs.getInt("price");
            String content = rs.getString("content");
            int newYear = rs.getInt("year");
            int newMonth = rs.getInt("month");
            int newDay = rs.getInt("day");
            reports.add(new Report(reportId, isIncome, paymentMethod, category, price, content, Report.getAccountBookName(), newYear, newMonth, newDay));
        }
    }

    public void insertReport(boolean isIncome, String paymentMethod, String category, int price, String memo, LocalDate date){
        Connection con = DBConnection.getConnection();
        String sql = "insert into report(is_income, payment_method, category, price, content, account_book_name, day, month, year) " +
                "value(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps;
        try {
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, isIncome);
            ps.setString(2, paymentMethod);
            ps.setString(3, category);
            ps.setInt(4, price);
            ps.setString(5, memo);
            ps.setString(6, Report.getAccountBookName());
            ps.setInt(7, date.getDayOfMonth());
            ps.setInt(8, date.getMonthValue());
            ps.setInt(9, date.getYear());
            ps.executeUpdate();
            con.commit();
            con.close();
            ps.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Report> findDayReport(int year, int month, int day) {
        List<Report> reports = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "select * from report where account_book_name=? and year=? and month=? and day=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, Report.getAccountBookName());
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

    public List<Report> findWeekReport(LocalDate startDate, LocalDate endDate) {
        return null;
    }

    public List<Report> findMonthReport(int year, int month) {
        List<Report> reports = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "select * from report where account_book_name=? and year=? and month=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, Report.getAccountBookName());
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

    public List<Report> findYearReport(int year) {
        List<Report> reports = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "select * from report where account_book_name=? and year=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, Report.getAccountBookName());
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

    public List<Report> findIsComeReport(boolean isInCome) {
        List<Report> reports = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "select * from report where account_book_name=? and is_income=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, Report.getAccountBookName());
            ps.setBoolean(2, isInCome);
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

    public List<Report> findIncomeCategoryReports(String content) {
        return findReportByIsInComeAndCategory(true, content);
    }

    public List<Report> findExpenseCategoryReports(String content) {
        return findReportByIsInComeAndCategory(false, content);
    }

    public List<Report> findReportByIsInComeAndCategory(boolean isIncome, String content) {
        List<Report> reports = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        String sql;
        if(isIncome) {
            sql = "select * from report where account_book_name=? and content=? and is_income=1";
        } else {
            sql = "select * from report where account_book_name=? and content=? and is_income=0";
        }

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, Report.getAccountBookName());
            ps.setString(2, content);
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

    public List<Report> findAllReport(String accountBookName) {
        List<Report> reports = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "select * from report where account_book_name=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, Report.getAccountBookName());

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

    public void delete(Integer report_id) {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        ResultSet rs;

        String sql = "delete from report where report_id = ?";
        try {
            ps = con.prepareStatement(sql);
            con.setAutoCommit(false);
            ps.setInt(1, report_id);

            rs = ps.executeQuery();

            con.commit();
            con.close();
            ps.close();
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void deleteBookCascade(String bookName) {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        String sql2 = "delete from report where account_book_name = ?";
        try {
            ps = con.prepareStatement(sql2);
            con.setAutoCommit(false);
            ps.setString(1, bookName);

            ps.executeUpdate();

            con.commit();
            con.close();
            ps.close();
        } catch (SQLException ex1) {
            ex1.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException ex2) {
                ex2.printStackTrace();
            }
        }
    }

    public void updateBookCascade(String changedBookName, String bookName) {
        Connection con = DBConnection.getConnection();
        PreparedStatement ps;
        String sql2 = "update report set account_book_name = ? where account_book_name = ?";

        try {
            ps = con.prepareStatement(sql2);
            con.setAutoCommit(false);
            ps.setString(1, changedBookName);
            ps.setString(2, bookName);

            ps.executeUpdate();

            con.commit();
            con.close();
            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
