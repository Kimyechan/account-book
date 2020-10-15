package com.company.accountbook.service.checkaccountbook;

import com.company.accountbook.dao.ReportDAO;
import com.company.accountbook.dto.AccountBook;
import com.company.accountbook.dto.Report;
import com.company.accountbook.vo.ExpenseCategory;
import com.company.accountbook.vo.IncomeCategory;

import java.util.List;

//public class DayCheckAccountBook implements CheckAccountBook {
//    ReportDAO reportDAO = new ReportDAO(new AccountBook("yechan"));
//    int year = 0;
//    int month = 0;
//    int day = 0;
//
//    public List<Report> checkReport(int year, int month, int day) {
//        this.year = year;
//        this.month = month;
//        this.day = day;
//        return reportDAO.getDayReport(year, month, day);
//    }
//
//    public void showStatics() {
//
//    }
//
//    void showStatics(ExpenseCategory expenseCategory) {
//
//    }
//
//    void showStatics(IncomeCategory incomeCategory) {
//
//    }
//
//    public void printAccount() {
//        System.out.println(checkReport(year, month, day));
//    }
//}