package com.company.accountbook.util;

import com.company.accountbook.dto.Report;
import com.company.accountbook.service.ReportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StaticsMenu {
    Scanner sc = new Scanner(System.in);
    private String number;
    private int year;
    private int month;
    private int day;
    private int expenseStatistics = 0;
    private int incomeStatistics = 0;

    ReportService reportService = new ReportService();

    // 싱글톤
    private static StaticsMenu instance;

    private StaticsMenu() {
    }

    public static StaticsMenu getInstance() {
        if (instance == null) {
            instance = new StaticsMenu();
        }
        return instance;
    }

    public void staticMenuPrint() {
        System.out.println("1. 일별 통계");
        System.out.println("2. 주별 통계");
        System.out.println("3. 월별 통계");
        System.out.println("4. 연도별 통계");
        System.out.println("0. 나가기");
        System.out.print(">> ");
        number = sc.nextLine();
        System.out.println();

        while (!number.equals("1") && !number.equals("2") && !number.equals("3") && !number.equals("4") && !number.equals("0")) {
            System.out.println("다시 입력하세요.");
            System.out.print(">> ");
            number = sc.nextLine();
            System.out.println();
        }

        if (number.equals("1")) {
            dayStatics();
        } else if (number.equals("2")) {
            weekStatics();
        } else if (number.equals("3")) {
            monthStatics();
        } else if (number.equals("4")) {
            yearStatics();
        } else {
            MainMenu.getInstance().mainMenuPrint();
        }

        StaticsMenu.getInstance().staticMenuPrint();
        System.out.println();
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

        List<Report> reportList = reportService.getDayReports(year, month, day);
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

        List<Report> reportList = reportService.getMonthReports(year, month);
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

        List<Report> reportList = reportService.getYearReports(year);
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