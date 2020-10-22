package com.company.accountbook.service;

import com.company.accountbook.dao.AccountBookDAO;
import com.company.accountbook.dao.ReportDAO;
import com.company.accountbook.dto.AccountBook;

import java.util.List;

public class AccountBookService {
    AccountBookDAO accountBookDAO = new AccountBookDAO();
    ReportDAO reportDAO = new ReportDAO();

    public void addAccountBook(String bookName, String pass) {
        accountBookDAO.insertAccountBook(bookName, pass);
    }

    public List<AccountBook> getAccountBooks() {
        return accountBookDAO.findAll();
    }

    public boolean checkExisting(String bookName) {
        AccountBook selectedBook = accountBookDAO.findByBookName(bookName);
        String selectedBookName = selectedBook.getBookName();

        return selectedBookName != null;
    }

    public boolean checkAccessRight(String bookName, String password) {
        AccountBook selectedBook = accountBookDAO.findByBookName(bookName);
        String rightPass = selectedBook.getPassword();

        return password.equals(rightPass);
    }

    public void updateAccountBook(String bookName, String changedBookName, String changedPass) {
        accountBookDAO.update(bookName, changedBookName, changedPass);
        reportDAO.updateBookCascade(changedBookName, bookName);
    }

    public void deleteAccountBook(String bookName) {
        accountBookDAO.delete(bookName);
        reportDAO.deleteBookCascade(bookName);
    }

}
