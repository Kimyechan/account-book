package com.company.accountbook.util;

import com.company.accountbook.service.ReportService;

import java.util.Scanner;

public class StaticsMenu {
    Scanner sc = new Scanner(System.in);
    private String number;
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
    }

    public void dayStatics() {
        System.out.println("조회를 원하는 날을 입력하세요. ex) 2020-10-15");
        System.out.print(">> ");
        try {
            String[] date = sc.nextLine().split("-");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            System.out.println(reportService.getDayReports(year, month, day));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            dayStatics();
        }
    }

    public void weekStatics() {}


    public void monthStatics() {
        System.out.println("조회를 원하는 달을 입력하세요. ex) 2020-10");
        System.out.print(">> ");
        try {
            String[] date = sc.nextLine().split("-");
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            System.out.println(reportService.getMonthReports(year, month));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            monthStatics();
        }
    }
    public void yearStatics() {
        System.out.println("조회를 원하는 연도를 입력하세요. ex) 2020");
        System.out.print(">> ");
        try {
            int year = sc.nextInt();
            System.out.println(reportService.getYearReports(year));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            yearStatics();
        }

    }
}