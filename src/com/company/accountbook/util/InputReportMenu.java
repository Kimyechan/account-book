package com.company.accountbook.util;

import com.company.accountbook.service.ReportService;

import java.time.LocalDate;
import java.util.Scanner;

public class InputReportMenu {
    ReportService reportService = new ReportService();
    Scanner sc = new Scanner(System.in);
    private String number;
    private int year;
    private int month;
    private int day;


    // 싱글톤
    private static InputReportMenu instance;

    private InputReportMenu() {
    }

    public static InputReportMenu getInstance() {
        if (instance == null) {
            instance = new InputReportMenu();
        }
        return instance;
    }

    public void inputReportMenuPrint() {
        System.out.println("1. 지출 입력");
        System.out.println("2. 수입 입력");
        System.out.println("0. 나가기");
        System.out.print(">> ");
        number = sc.nextLine();
        System.out.println();

        while (!number.equals("1") && !number.equals("2") && !number.equals("0")) {
            System.out.println("다시 입력하세요.");
            System.out.print(">> ");
            number = sc.nextLine();
            System.out.println();
        }

        if (number.equals("1")) {
            inputExpenseReport();
        } else if (number.equals("2")) {
            inputIncomeReport();
        } else {

        }
    }

    public void inputExpenseReport() {
        try {
            System.out.print("날짜: ");
            String[] date = sc.nextLine().split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            day = Integer.parseInt(date[2]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            System.out.println("형식에 맞게 다시 입력하세요.");
            System.out.println(e.getMessage());
            inputIncomeReport();
        }
        System.out.print("지출 내역: ");
        String content = sc.nextLine();
        System.out.print("금액: ");
        int price = Integer.parseInt(sc.nextLine());
        System.out.print("메모: ");
        String memo = sc.nextLine();
//        reportService.addReport(false, content, price, memo, LocalDate.of(year, month, day));
    }

    public void inputIncomeReport() {
        try {
            System.out.print("날짜: ");
            String[] date = sc.nextLine().split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            day = Integer.parseInt(date[2]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            System.out.println("형식에 맞게 다시 입력하세요.");
            System.out.println(e.getMessage());
            inputExpenseReport();
        }
        System.out.print("수입 내역: ");
        String content = sc.nextLine();
        System.out.print("금액: ");
        int price = Integer.parseInt(sc.nextLine());
        System.out.print("메모: ");
        String memo = sc.nextLine();
//        reportService.addReport(true, content, price, memo, LocalDate.of(year, month, day));
    }
}