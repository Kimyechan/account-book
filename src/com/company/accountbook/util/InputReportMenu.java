package com.company.accountbook.util;

import com.company.accountbook.dao.ReportDAO;
import com.company.accountbook.dto.AccountBook;

import java.time.LocalDate;
import java.util.Scanner;

public class InputReportMenu {
    ReportDAO reportDAO = new ReportDAO(new AccountBook(""));
    Scanner sc = new Scanner(System.in);
    private String number;

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
        System.out.print("날짜: ");
        String[] date = sc.nextLine().split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        System.out.print("지출 내역: ");
        String content = sc.nextLine();
        System.out.print("금액: ");
        int price = sc.nextInt();
        System.out.print("메모: ");
        String memo = sc.nextLine();
        reportDAO.insertReport(false, content, price, memo, LocalDate.of(year, month, day));
    }

    public void inputIncomeReport() {
        System.out.print("날짜: ");
        String[] date = sc.nextLine().split("-");
        int year = Integer.parseInt(date[0]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[2]);
        System.out.print("수입 내역: ");
        String content = sc.nextLine();
        System.out.print("금액: ");
        int price = sc.nextInt();
        System.out.print("메모: ");
        String memo = sc.nextLine();
        reportDAO.insertReport(true, content, price, memo, LocalDate.of(year, month, day));
    }
}