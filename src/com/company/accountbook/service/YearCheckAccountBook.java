package com.company.accountbook.service;

import com.company.accountbook.dao.AccountBookDAO;
import com.company.accountbook.dto.Report;
import com.company.accountbook.vo.ExpenseCategory;
import com.company.accountbook.vo.IncomeCategory;

import java.util.List;

public class YearCheckAccountBook implements CheckAccountBook {
    AccountBookDAO accountBookDAO = new AccountBookDAO();
    int year;
    int month = 0;
    int day = 0;

    public List<Report> checkReport(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        return accountBookDAO.getReport(year, 0, 0);
    }

    public void showStatics() {

    }

    void showStatics(ExpenseCategory expenseCategory) {

    }

    void showStatics(IncomeCategory incomeCategory) {

    }

    public void printAccount() {
        System.out.println(checkReport(year, 0, 0));
    }
}
