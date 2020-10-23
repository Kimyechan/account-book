package com.company.accountbook.util;

import com.company.accountbook.dto.Report;
import com.company.accountbook.service.ReportService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckMenu {
    ReportService reportService = new ReportService();
    Scanner sc = new Scanner(System.in);
    private int year;
    private int month;
    private int day;


    // 싱글톤
    private static CheckMenu instance;

    private CheckMenu() {
    }

    public static CheckMenu getInstance() {
        if (instance == null) {
            instance = new CheckMenu();
        }
        return instance;
    }

    public void checkMenuPrint() {
        System.out.println("1. 일별 조회");
        System.out.println("2. 주별 조회");
        System.out.println("3. 월별 조회");
        System.out.println("4. 연도별 조회");
        System.out.println("0. 나가기");
        System.out.print(">> ");
        String number = sc.nextLine();
        System.out.println();

        while (!number.equals("1") && !number.equals("2") && !number.equals("3") && !number.equals("4") && !number.equals("0")) {
            System.out.println("다시 입력하세요.");
            number = sc.nextLine();
            System.out.println();
        }

        if (number.equals("1")) {
            dayCheckReport();
        } else if (number.equals("2")) {
            weekCheckReport();
        } else if (number.equals("3")) {
            monthCheckReport();
        } else if (number.equals("4")) {
            yearCheckReport();
        } else {
            MainMenu.getInstance().mainMenuPrint();
        }
        System.out.println();
        checkMenuPrint();
    }

    public void dayCheckReport() {
        System.out.println("조회를 원하는 날을 입력하세요. ex) 2020-10-15");
        System.out.print(">> ");
        try {
            String[] date = sc.nextLine().split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            day = Integer.parseInt(date[2]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            dayCheckReport();
        }
        System.out.println();
        if (reportService.getDayReports(year, month, day).isEmpty()) {
            System.out.println("내역 없음");
        } else {
            printReport(reportService.getDayReports(year, month, day));
        }
    }

    public void weekCheckReport() {
    }

    public void monthCheckReport() {
        System.out.println("조회를 원하는 달을 입력하세요. ex) 2020-10");
        System.out.print(">> ");
        try {
            String[] date = sc.nextLine().split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            monthCheckReport();
        }
        System.out.println();
        if (reportService.getMonthReports(year, month).isEmpty()) {
            System.out.println("내역 없음");
        } else {
            printReport(reportService.getMonthReports(year, month));
        }
    }

    public void yearCheckReport() {
        System.out.println("조회를 원하는 연도를 입력하세요. ex) 2020");
        System.out.print(">> ");
        try {
            year = Integer.parseInt(sc.nextLine());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            yearCheckReport();
        }
        System.out.println();
        if (reportService.getYearReports(year).isEmpty()) {
            System.out.println("내역 없음");
        } else {
            printReport(reportService.getYearReports(year));
        }
    }

    public void printReport(List<Report> reportList) {
        List<Report> expense = new ArrayList<>();
        List<Report> income = new ArrayList<>();
        for (Report report : reportList) {
            if (report.isIncome()) {
                income.add(report);
            } else {
                expense.add(report);
            }
        }
        System.out.println("***** 지출 내역 *****\n");
        if (expense.isEmpty()) {
            System.out.println("내역 없음");
        } else {
            expense.forEach(System.out::println);
        }
        System.out.println("\n");
        System.out.println("***** 수입 내역 *****\n");
        if (income.isEmpty()) {
            System.out.println("내역 없음");
        } else {
            income.forEach(System.out::println);
        }
    }
}