package com.company.accountbook.service;

import com.company.accountbook.dao.ReportDAO;
import com.company.accountbook.dto.Report;
import com.company.accountbook.vo.ExpenseCategory;
import com.company.accountbook.vo.IncomeCategory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    ReportDAO reportDAO = new ReportDAO();

    public void addReport(boolean isIncome, String paymentMethod, String category, int price, String content, LocalDate date) {
        reportDAO.insertReport(isIncome, paymentMethod, category, price, content, date);
    }

    public List<Report> getDayReports(int year, int month, int day) {
        return reportDAO.findDayReport(year, month, day);
    }

    public List<Report> getMonthReports(int year, int month) {
        return reportDAO.findMonthReport(year, month);
    }

    public List<Report> getYearReports(int year) {
        return reportDAO.findYearReport(year);
    }

    public List<Report> getIsComeReports(boolean isIncome) {
        return reportDAO.findIsComeReport(isIncome);
    }

    public List<Report> getIncomeCategoryReports(IncomeCategory category) {
        String content = category.toString();
        return reportDAO.findIncomeCategoryReports(content);
    }

    public List<Report> getExpenseCategoryReports(ExpenseCategory category) {
        String content = category.toString();
        return reportDAO.findExpenseCategoryReports(content);
    }

    public void setBookNameForReportList(String bookName) {
        Report.setAccountBookName(bookName);
    }

    public Map<String, Double> getDayExpenseStatics(int year, int month, int day) {
        List<Report> reports = getDayReports(year, month, day);
        return calculateExpense(reports);
    }

    public Map<String, Double> getMonthExpenseStatics(int year, int month) {
        List<Report> reports = getMonthReports(year, month);
        return calculateExpense(reports);
    }

    public Map<String, Double> getYearExpenseStatics(int year) {
        List<Report> reports = getYearReports(year);
        return calculateExpense(reports);
    }

    private Map<String, Double> calculateExpense(List<Report> reports) {
        Map<String, Double> statics = new HashMap<>();

        Integer priceOfFood = getCategoryPrice(reports, ExpenseCategory.FOOD);
        Integer priceOfCloth = getCategoryPrice(reports, ExpenseCategory.CLOTH);
        Integer priceOfTax = getCategoryPrice(reports, ExpenseCategory.TAX);

        Integer sum = priceOfFood + priceOfCloth + priceOfTax;

        statics.put("food", (double) (priceOfFood / sum) * 100);
        statics.put("cloth", (double) (priceOfCloth / sum) * 100);
        statics.put("tax", (double) (priceOfTax / sum) * 100);

        return statics;
    }

    public Integer getCategoryPrice(List<Report> reports, ExpenseCategory category) {
        return reports.stream()
                .filter(report -> report.getCategory().equals(category.toString()))
                .map(Report::getPrice)
                .reduce(0, Integer::sum);
    }

    public Integer calculateCurrentAllMoney() {
        List<Report> reports = reportDAO.findAllReport(Report.getAccountBookName());

        return getCurrentMoneyStream(reports);
    }

    public Integer calculateCurrentMonthMoney(int year, int month) {
        List<Report> reports = reportDAO.findMonthReport(year, month);

        return getCurrentMoneyStream(reports);
    }

    public Integer calculateCurrentYearMoney(int year) {
        List<Report> reports = reportDAO.findYearReport(year);

        return getCurrentMoneyStream(reports);
    }

    public Integer getCurrentMoneyStream(List<Report> reports) {
        return reports.stream().filter(report -> !report.isIncome()).map(Report::getPrice).reduce(0, Integer::sum)
                - reports.stream().filter(Report::isIncome).map(Report::getPrice).reduce(0, Integer::sum);
    }
    public void showStatics() {

    }

}