package com.company.accountbook.service;

import com.company.accountbook.dao.AccountBookDAO;
import com.company.accountbook.dto.Report;
import com.company.accountbook.vo.ExpenseCategory;
import com.company.accountbook.vo.IncomeCategory;

import java.util.List;

public class MonthCheckAccountBook implements CheckAccountBook {
    AccountBookDAO accountBookDAO = new AccountBookDAO();
    int year = 0;
    int month = 0;
    int day = 0;

    public List<Report> checkReport(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        return accountBookDAO.getReport(year, month, 0);
    }

    public void showStatics() {

    }

    void showStatics(ExpenseCategory expenseCategory) {

    }

    void showStatics(IncomeCategory incomeCategory) {

    }

    public void printAccount() {

    }

    public void printCalendar() {
        // 기본값으로 이번 달 출력
    }

    public void printCalendar(int year, int month) {

    }
}
