package com.company.accountbook.service;

import com.company.accountbook.dao.AccountBookDAO;
import com.company.accountbook.dto.Report;

import java.time.LocalDate;
import java.util.List;

public class CheckAccountBook {
    AccountBookDAO accountBookDAO = new AccountBookDAO();

    public List<Report> checkReport(int year, int month, int day) {
        return accountBookDAO.getDayReport(year, month, day);
    }

    public List<Report> checkReport(int year, int month) {
        return accountBookDAO.getMonthReport(year, month);
    }

    public List<Report> checkReport(int year) {
        return accountBookDAO.getYearReport(year);
    }

    public List<Report> checkReport(LocalDate startDate, LocalDate endDate) {
        return accountBookDAO.getWeekReport(startDate, endDate);
    }

    void showStatics() {

    }

    void printAccount() {

    }
}