package com.company.accountbook.dao;

import com.company.accountbook.dto.Report;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ReportDAO {

    void insertReport(boolean isIncome, String paymentMethod, String category, int price, String memo, LocalDate date);

    List<Report> findDayReport(int year, int month, int day);

    List<Report> findWeekReport(LocalDate startDate, LocalDate endDate);

    List<Report> findMonthReport(int year, int month);

    List<Report> findYearReport(int year);

    List<Report> findIsComeReport(boolean isInCome);

    List<Report> findIncomeCategoryReports(String content);

    List<Report> findExpenseCategoryReports(String content);

    List<Report> findReportByIsInComeAndCategory(boolean isIncome, String content);

    List<Report> findAllReport(String accountBookName);

    void delete(Integer reportId);

    void deleteBookCascade(String bookName);

    void updateBookCascade(String changedBookName, String bookName);
}
