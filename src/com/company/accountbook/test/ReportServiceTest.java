package com.company.accountbook.test;

import com.company.accountbook.dto.Report;
import com.company.accountbook.service.ReportService;
import com.company.accountbook.vo.ExpenseCategory;
import com.company.accountbook.vo.IncomeCategory;
import com.company.accountbook.vo.PayCategory;

import java.time.LocalDate;
import java.util.List;

public class ReportServiceTest {
    public static void addData() {
        ReportService reportService = new ReportService();

        reportService.addReport(false, PayCategory.CARD.toString(), ExpenseCategory.FOOD.toString(), 10000, "gukbab lunch", LocalDate.of(2020, 10, 16));
        reportService.addReport(false, PayCategory.CARD.toString(), ExpenseCategory.FOOD.toString(), 10000, "lunch", LocalDate.of(2020, 10, 16));
        reportService.addReport(false, PayCategory.CARD.toString(), ExpenseCategory.FOOD.toString(), 8000, "lunch", LocalDate.of(2020, 10, 16));
        reportService.addReport(false, PayCategory.CARD.toString(), ExpenseCategory.FOOD.toString(), 8000, "lunch", LocalDate.of(2020, 10, 16));
        reportService.addReport(false, PayCategory.CARD.toString(), ExpenseCategory.FOOD.toString(), 8000, "lunch", LocalDate.of(2020, 11, 16));
        reportService.addReport(false, PayCategory.CARD.toString(), ExpenseCategory.FOOD.toString(), 8000, "lunch", LocalDate.of(2020, 11, 16));
        reportService.addReport(false, PayCategory.CARD.toString(), ExpenseCategory.FOOD.toString(), 8000, "lunch", LocalDate.of(2021, 11, 16));
        reportService.addReport(false, PayCategory.CARD.toString(), ExpenseCategory.FOOD.toString(), 8000, "lunch", LocalDate.of(2021, 11, 16));
        reportService.addReport(true,  PayCategory.CARD.toString(), IncomeCategory.SALARY.toString(), 100000000, "11 month salary", LocalDate.of(2021, 11, 1));
        reportService.addReport(true,  PayCategory.CARD.toString(), IncomeCategory.SALARY.toString(), 30000000, "11 month bonus", LocalDate.of(2021, 11, 1));
    }

    public static void main(String[] args) {
        ReportService reportService = new ReportService();

        reportService.setBookNameForReportList("yechan");
        ReportServiceTest.addData();

        List<Report> reports = reportService.getDayReports(2020, 10, 16);
        for(Report report : reports) {
            System.out.println(report);
        }

        System.out.println();

        List<Report> reports1 = reportService.getMonthReports(2021, 11);
        for(Report report : reports1) {
            System.out.println(report);
        }

        System.out.println();

        List<Report> reports2 = reportService.getYearReports(2020);
        for(Report report : reports2) {
            System.out.println(report);
        }

        System.out.println();

        List<Report> reports3 = reportService.getExpenseCategoryReports(ExpenseCategory.FOOD);
        for(Report report : reports3) {
            System.out.println(report);
        }

        System.out.println();

        List<Report> reports4 = reportService.getIncomeCategoryReports(IncomeCategory.SALARY);
        for(Report report : reports4) {
            System.out.println(report);
        }

        System.out.println();

        List<Report> reports5 = reportService.getIsComeReports(true);
        for(Report report : reports5) {
            System.out.println(report);
        }

        System.out.println(reportService.getDayExpenseStatics(2020, 10, 16));
    }
}
