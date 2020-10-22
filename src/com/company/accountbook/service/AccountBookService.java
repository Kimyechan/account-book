package com.company.accountbook.service;

import com.company.accountbook.dao.AccountBookDAOImpl;
import com.company.accountbook.dao.ReportDAOImpl;
import com.company.accountbook.dto.AccountBook;

import java.util.List;

public class AccountBookService {
    AccountBookDAOImpl accountBookDAOImpl = new AccountBookDAOImpl();
    ReportDAOImpl reportDAOImpl = new ReportDAOImpl();

    public void addAccountBook(String bookName, String pass) {
        accountBookDAOImpl.insertAccountBook(bookName, pass);
    }

    public List<AccountBook> getAccountBooks() {
        return accountBookDAOImpl.findAll();
    }

    public boolean checkExisting(String bookName) {
        AccountBook selectedBook = accountBookDAOImpl.findByBookName(bookName);
        String selectedBookName = selectedBook.getBookName();

        return selectedBookName != null;
    }

    public boolean checkAccessRight(String bookName, String password) {
        AccountBook selectedBook = accountBookDAOImpl.findByBookName(bookName);
        String rightPass = selectedBook.getPassword();

        return password.equals(rightPass);
    }

    public void updateAccountBook(String bookName, String changedBookName, String changedPass) {
        accountBookDAOImpl.update(bookName, changedBookName, changedPass);
        reportDAOImpl.updateBookCascade(changedBookName, bookName);
    }

    public void deleteAccountBook(String bookName) {
        accountBookDAOImpl.delete(bookName);
        reportDAOImpl.deleteBookCascade(bookName);
    }

}
