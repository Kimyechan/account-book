package com.company.accountbook.service;

import com.company.accountbook.dao.AccountBookDAO;
import com.company.accountbook.dao.ReportDAO;
import com.company.accountbook.dto.AccountBook;
import com.company.accountbook.dto.Report;
import com.company.accountbook.vo.ExpenseCategory;
import com.company.accountbook.vo.IncomeCategory;

import java.util.List;

public class MonthCheckAccountBook implements CheckAccountBook {
    ReportDAO reportDAO = new ReportDAO(new AccountBook("yechan"));
    int year = 0;
    int month = 0;
    int day = 0;

    public List<Report> checkReport(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
        return reportDAO.getMonthReport(year, month);
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
