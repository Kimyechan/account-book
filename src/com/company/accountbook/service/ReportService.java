package com.company.accountbook.service;

import com.company.accountbook.dao.ReportDAO;
import com.company.accountbook.dto.Report;
import com.company.accountbook.vo.ExpenseCategory;
import com.company.accountbook.vo.IncomeCategory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReportService {
    ReportDAO reportDAO = new ReportDAO();
    Scanner sc = new Scanner(System.in);
    private int incomeStatistics;
    private int expenseStatistics;
    private int year;
    private int month;
    private int day;

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

    public Map<String, Double> getMonthIncomeStatics(int year, int month) {
        List<Report> reports = getMonthReports(year, month);
        return calculateIncome(reports);
    }

    public Map<String, Double> getYearIncomeStatics(int year) {
        List<Report> reports = getYearReports(year);
        return calculateIncome(reports);
    }

    private Map<String, Double> calculateIncome(List<Report> reports) {
        Map<String, Double> statics = new HashMap<>();

        Integer priceOfSALARY = getCategoryPrice(reports, IncomeCategory.SALARY);
        Integer priceOfPOCKET = getCategoryPrice(reports, IncomeCategory.POCKET_MONEY);
        Integer priceOfFINANCIAL = getCategoryPrice(reports, IncomeCategory.FINANCIAL_MONEY);
        Integer priceOfETC = getCategoryPrice(reports, IncomeCategory.ETC);

        Integer sum = priceOfSALARY + priceOfPOCKET + priceOfFINANCIAL + priceOfETC;

        statics.put("salary", (double) (priceOfSALARY / sum) * 100);
        statics.put("pocket", (double) (priceOfPOCKET / sum) * 100);
        statics.put("financial", (double) (priceOfFINANCIAL / sum) * 100);
        statics.put("etc", (double) (priceOfETC / sum) * 100);

        return statics;
    }

    public Integer getCategoryPrice(List<Report> reports, IncomeCategory category) {
        return reports.stream()
                .filter(report -> report.getCategory().equals(category.toString()))
                .map(Report::getPrice)
                .reduce(0, Integer::sum);
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
        List<Report> reports = reportDAO.findAllReport(Report.getAccountBookName());

        return getCurrentMoneyStream(reports);
    }

    public Map<String, Integer> calculateCurrentMonthMoney(int year, int month) {
        List<Report> reports = reportDAO.findMonthReport(year, month);

        return getCurrentMoneyStream(reports);
    }

    public Map<String, Integer> calculateCurrentYearMoney(int year) {
        List<Report> reports = reportDAO.findYearReport(year);

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

    public void showStatics(Map<String, Double> map) {
        if (map.isEmpty()) {
            System.out.println("- 내역 없음");
        } else {
            for (Map.Entry<String, Double> entry : map.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    public int sumAllPrice(Map<String, Integer> map) {
        int sumPrice = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
        }
        return 0;
    }

    public void dayStatics() {
        System.out.println("조회를 원하는 날을 입력하세요. ex) 2020-10-15");
        System.out.print(">> ");
        try {
            String[] date = sc.nextLine().split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            day = Integer.parseInt(date[2]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            dayStatics();
        }

        List<Report> reportList = getDayReports(year, month, day);
        HashMap<String, Integer> expense = new HashMap<>();
        HashMap<String, Integer> income = new HashMap<>();
        while (!reportList.isEmpty()) {
            Report report = reportList.remove(0);
            if (report.isIncome()) {
                income.put(report.getCategory(), income.getOrDefault(report.getCategory(), 0) + report.getPrice());
            } else {
                expense.put(report.getCategory(), expense.getOrDefault(report.getCategory(), 0) + report.getPrice());
            }
        }
        System.out.println();
        System.out.println("지출");
//        if (reportService.getDayExpenseStatics(year, month, day).isEmpty()) {
//            System.out.println("- 내역 없음");
//        } else {
//            reportService.showStatics(reportService.getDayExpenseStatics(year, month, day));
//        }

        if (expense.isEmpty()) {
            System.out.println("- 내역 없음");
        } else {
            for (Map.Entry<String, Integer> entry : expense.entrySet()) {
                expenseStatistics += entry.getValue();
            }
            for (Map.Entry<String, Integer> entry : expense.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " (" + ((float) entry.getValue() * 100 / (float) expenseStatistics) + " %)");
            }
        }

        System.out.println();
        System.out.println("수입");
        if (income.isEmpty()) {
            System.out.println("- 내역 없음");
        } else {
            for (Map.Entry<String, Integer> entry : income.entrySet()) {
                incomeStatistics += entry.getValue();
            }
            for (Map.Entry<String, Integer> entry : income.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " (" + ((float) entry.getValue() * 100 / (float) incomeStatistics) + " %)");
            }
        }
        System.out.println();
        System.out.println("총 지출 금액: " + expenseStatistics);
        System.out.println("총 수입 금액: " + incomeStatistics);
    }

    public void weekStatics() {
    }


    public void monthStatics() {
        System.out.println("조회를 원하는 달을 입력하세요. ex) 2020-10");
        System.out.print(">> ");
        try {
            String[] date = sc.nextLine().split("-");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            monthStatics();
        }

        List<Report> reportList = getMonthReports(year, month);
        HashMap<String, Integer> expense = new HashMap<>();
        HashMap<String, Integer> income = new HashMap<>();
        while (!reportList.isEmpty()) {
            Report report = reportList.remove(0);
            System.out.println(report.isIncome());
            if (report.isIncome()) {
                income.put(report.getCategory(), income.getOrDefault(report.getCategory(), 0) + report.getPrice());
            } else {
                expense.put(report.getCategory(), expense.getOrDefault(report.getCategory(), 0) + report.getPrice());
            }
        }
        System.out.println();
        System.out.println("지출");
        if (expense.isEmpty()) {
            System.out.println("- 내역 없음");
        } else {
            for (Map.Entry<String, Integer> entry : expense.entrySet()) {
                expenseStatistics += entry.getValue();
            }
            for (Map.Entry<String, Integer> entry : expense.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " (" + ((float) entry.getValue() * 100 / (float) expenseStatistics) + " %)");
            }
        }

        System.out.println();
        System.out.println("수입");
        if (income.isEmpty()) {
            System.out.println("- 내역 없음");
        } else {
            for (Map.Entry<String, Integer> entry : income.entrySet()) {
                incomeStatistics += entry.getValue();
            }
            for (Map.Entry<String, Integer> entry : income.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " (" + ((float) entry.getValue() * 100 / (float) incomeStatistics) + " %)");
            }
        }
        System.out.println();
        System.out.println("총 지출 금액: " + expenseStatistics);
        System.out.println("총 수입 금액: " + incomeStatistics);
    }

    public void yearStatics() {
        System.out.println("조회를 원하는 연도를 입력하세요. ex) 2020");
        System.out.print(">> ");
        try {
            int year = sc.nextInt();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            yearStatics();
        }

        List<Report> reportList = getYearReports(year);
        HashMap<String, Integer> expense = new HashMap<>();
        HashMap<String, Integer> income = new HashMap<>();
        while (!reportList.isEmpty()) {
            Report report = reportList.remove(0);
            System.out.println(report.isIncome());
            if (report.isIncome()) {
                income.put(report.getCategory(), income.getOrDefault(report.getCategory(), 0) + report.getPrice());
            } else {
                expense.put(report.getCategory(), expense.getOrDefault(report.getCategory(), 0) + report.getPrice());
            }
        }
        System.out.println();
        System.out.println("지출");
        if (expense.isEmpty()) {
            System.out.println("- 내역 없음");
        } else {
            for (Map.Entry<String, Integer> entry : expense.entrySet()) {
                expenseStatistics += entry.getValue();
            }
            for (Map.Entry<String, Integer> entry : expense.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " (" + ((float) entry.getValue() * 100 / (float) expenseStatistics) + " %)");
            }
        }

        System.out.println();
        System.out.println("수입");
        if (income.isEmpty()) {
            System.out.println("- 내역 없음");
        } else {
            for (Map.Entry<String, Integer> entry : income.entrySet()) {
                incomeStatistics += entry.getValue();
            }
            for (Map.Entry<String, Integer> entry : income.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " (" + ((float) entry.getValue() * 100 / (float) incomeStatistics) + " %)");
            }
        }
        System.out.println();
        System.out.println("총 지출 금액: " + expenseStatistics);
        System.out.println("총 수입 금액: " + incomeStatistics);
        System.out.println();
    }
}