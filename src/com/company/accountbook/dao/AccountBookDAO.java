package com.company.accountbook.dao;

import com.company.accountbook.dto.AccountBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountBookDAO {
    public AccountBookDAO() {
        DBConnection.setDBServer();
    }

    public void insertAccountBook(String username, String password){
        Connection con = DBConnection.getConnection();
        String sql = "insert into account_book value(?, ?)";
        PreparedStatement ps;
        try {

            con.setAutoCommit(false);

            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();

            con.commit();
        } catch(SQLException ex) {
            ex.printStackTrace();
//            try(con.rollback()) {
//
//            }
        }
    }

    public List<AccountBook> findAll() {
        Connection con = DBConnection.getConnection();
        String sql = "select * from account_book";
        Statement stat;
        ResultSet rs;
        List<AccountBook> accountBooks = new ArrayList<>();
        try{
            stat = con.createStatement();
            rs = stat.executeQuery(sql);

            while(rs.next()) {
                String bookName = rs.getString("book_name");
                String pass = rs.getString("pass");

                accountBooks.add(new AccountBook(bookName, pass));
            }
            stat.close();
            con.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return accountBooks;
    }

    public AccountBook findByBookName(String bookName) {
        Connection con = DBConnection.getConnection();
        String sql = "select * from account_book where book_name=?";
        PreparedStatement ps;
        ResultSet rs;
        String selectedBookName = null;
        String selectedBookPass = null;

        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, bookName);
            rs = ps.executeQuery();

            while(rs.next()) {
                selectedBookName = rs.getString("book_name");
                selectedBookPass = rs.getString("pass");
            }

            con.close();
            ps.close();
            rs.close();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }

        return new AccountBook(selectedBookName, selectedBookPass);
    }
}
