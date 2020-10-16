package com.company.accountbook.service;

import com.company.accountbook.dao.AccountBookDAO;
import com.company.accountbook.dto.AccountBook;

import java.util.List;

public class AccountBookService {
    AccountBookDAO accountBookDAO = new AccountBookDAO();

    public void addAccountBook(String bookName, String pass) {
        accountBookDAO.insertAccountBook(bookName, pass);
    }

    public List<AccountBook> getAccountBooks() {
        return accountBookDAO.findAll();
    }

    public boolean checkExisting(String bookName) {
        AccountBook selectedBook = accountBookDAO.findByBookName(bookName);
        String selectedBookName = selectedBook.getBookName();

        if (selectedBookName != null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkAccessRight(String bookName, String password) {
        AccountBook selectedBook = accountBookDAO.findByBookName(bookName);
        String rightPass = selectedBook.getPassword();

        if(password.equals(rightPass)) {
            return true;
        } else {
            return false;
        }
    }
}
