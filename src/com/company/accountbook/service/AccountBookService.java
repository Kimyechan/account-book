package com.company.accountbook.service;

import com.company.accountbook.dao.AccountBookDAO;
import com.company.accountbook.dto.AccountBook;

import java.util.List;

public class AccountBookService {
    AccountBookDAO accountBookDAO = new AccountBookDAO();

    public void addAccountBook(String bookName, String pass){
        accountBookDAO.insertAccountBook(bookName, pass);
    }

    public List<AccountBook> getAccountBooks() {
        return accountBookDAO.findAll();
    }

    public boolean isExistedAccountBook(String bookName) {
        String selectedBookName = accountBookDAO.findByBookName(bookName);

        if(selectedBookName != null) {
            return true;
        } else {
            return false;
        }
    }
}
