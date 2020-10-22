package com.company.accountbook.dao;

import com.company.accountbook.dto.AccountBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface AccountBookDAO  {
    void insertAccountBook(String username, String password);

    List<AccountBook> findAll();

    AccountBook findByBookName(String bookName);

    void update(String bookName, String changedBookName, String changedPass);

    void delete(String bookName);
}
