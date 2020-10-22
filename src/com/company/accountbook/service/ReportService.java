package com.company.accountbook.service;

import com.company.accountbook.dao.ReportDAOImpl;
import com.company.accountbook.dto.Report;
import com.company.accountbook.vo.ExpenseCategory;
import com.company.accountbook.vo.IncomeCategory;

import java.time.LocalDate;
import java.util.*;

public class ReportService {
    ReportDAOImpl reportDAOImpl = new ReportDAOImpl();

    public void addReport(boolean isIncome, String paymentMethod, String category, int price, String content, LocalDate date) {
        reportDAOImpl.insertReport(isIncome, paymentMethod, category, price, content, date);
    }

    public List<Report> getDayReports(int year, int month, int day) {
        return reportDAOImpl.findDayReport(year, month, day);
    }

    public List<Report> getMonthReports(int year, int month) {
        return reportDAOImpl.findMonthReport(year, month);
    }

    public List<Report> getYearReports(int year) {
        return reportDAOImpl.findYearReport(year);
    }

    public List<Report> getIsComeReports(boolean isIncome) {
        return reportDAOImpl.findIsComeReport(isIncome);
    }

    public List<Report> getIncomeCategoryReports(IncomeCategory category) {
        String content = category.toString();
        return reportDAOImpl.findIncomeCategoryReports(content);
    }

    public List<Report> getExpenseCategoryReports(ExpenseCategory category) {
        String content = category.toString();
        return reportDAOImpl.findExpenseCategoryReports(content);
    }

    public void setBookNameForReportList(String bookName) {
        Report.setAccountBookName(bookName);
    }

    public Map<String, Integer> getDayExpenseStatics(int year, int month, int day) {
        List<Report> reports = getDayReports(year, month, day);
        return getStatistics(reports);
    }

    public Map<String, Integer> getMonthExpenseStatics(int year, int month) {
        List<Report> reports = getMonthReports(year, month);
        return getStatistics(reports);
    }

    public Map<String, Integer> getYearExpenseStatics(int year) {
        List<Report> reports = getYearReports(year);
        return getStatistics(reports);
    }

    private Map<String, Integer> getStatistics(List<Report> reports) {
        Map<String, Integer> statistics = new HashMap<>();
        for (Report report : reports) {
            statistics.put(report.getCategory(), statistics.getOrDefault(report.getCategory(), 0) + report.getPrice());
        }
        return statistics;
    }

    public Integer getCategoryPrice(List<Report> reports, ExpenseCategory category) {
        return reports.stream()
                .filter(report -> report.getCategory().equals(category.toString()))
                .map(Report::getPrice)
                .reduce(0, Integer::sum);
    }

    public Map<String, Integer> getDayIncomeStatics(int year, int month, int day) {
        List<Report> reports = getDayReports(year, month, day);
        return getStatistics(reports);
    }

    public Map<String, Integer> getMonthIncomeStatics(int year, int month) {
        List<Report> reports = getMonthReports(year, month);
        return getStatistics(reports);
    }

    public Map<String, Integer> getYearIncomeStatics(int year) {
        List<Report> reports = getYearReports(year);
        return getStatistics(reports);
    }

    public Integer getCategoryPrice(List<Report> reports, IncomeCategory category) {
        if (reports.isEmpty()) {
            return 0;
        } else {
            return reports.stream()
                    .filter(report -> report.getCategory().equals(category.toString()))
                    .map(Report::getPrice)
                    .reduce(0, Integer::sum);
        }
    }


    public Integer getExpenseAllStatics() {
        List<Report> reports = getIsComeReports(false);

        return reports.stream().map(Report::getPrice).reduce(0, Integer::sum);
    }

    public Integer getIncomeAllStatics() {
        List<Report> reports = getIsComeReports(true);

        return reports.stream().map(Report::getPrice).reduce(0, Integer::sum);
    }

    public Map<String, Integer> calculateCurrentAllMoney() {
        List<Report> reports = reportDAOImpl.findAllReport(Report.getAccountBookName());

        return getCurrentMoneyStream(reports);
    }

    public Map<String, Integer> calculateCurrentMonthMoney(int year, int month) {
        List<Report> reports = reportDAOImpl.findMonthReport(year, month);

        return getCurrentMoneyStream(reports);
    }

    public Map<String, Integer> calculateCurrentYearMoney(int year) {
        List<Report> reports = reportDAOImpl.findYearReport(year);

        return getCurrentMoneyStream(reports);
    }

    public Map<String, Integer> getCurrentMoneyStream(List<Report> reports) {
        Map<String, Integer> map = new HashMap<>();

        Integer expense = reports.stream().filter(report -> !report.isIncome()).map(Report::getPrice).reduce(0, Integer::sum);
        map.put("expense", expense);

        Integer income = reports.stream().filter(Report::isIncome).map(Report::getPrice).reduce(0, Integer::sum);
        map.put("income", income);

        return map;
    }

    public void deleteReport(Integer reportId) {
        reportDAOImpl.delete(reportId);
    }
}