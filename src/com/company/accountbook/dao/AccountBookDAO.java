package com.company.accountbook.dao;

import com.company.accountbook.dto.Report;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class AccountBookDAO {

    public void addAccountBook(String username){
        Connection con = DBConnection.getConnection();
        String sql = "insert into account_book value(?)";
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}
