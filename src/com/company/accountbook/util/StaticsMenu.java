package com.company.accountbook.util;

import com.company.accountbook.service.ReportService;

import java.util.Scanner;

public class StaticsMenu {
    Scanner sc = new Scanner(System.in);
    private String number;
    private int year;
    private int month;
    private int day;

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
            reportService.dayStatics();
            System.out.println();
        } else if (number.equals("2")) {
            reportService.weekStatics();
            System.out.println();
        } else if (number.equals("3")) {
            reportService.monthStatics();
            System.out.println();
        } else if (number.equals("4")) {
            reportService.yearStatics();
            System.out.println();
        } else {
            MainMenu.getInstance().mainMenuPrint();
            System.out.println();
        }

        StaticsMenu.getInstance().staticMenuPrint();
        System.out.println();
    }
}