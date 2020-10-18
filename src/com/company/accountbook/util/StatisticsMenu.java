package com.company.accountbook.util;

import com.company.accountbook.service.ReportService;

import java.util.Scanner;

public class StatisticsMenu {
    Scanner sc = new Scanner(System.in);
    InputDateMenu inputDateMenu = InputDateMenu.getInstance();

    ReportService reportService = new ReportService();

    // 싱글톤
    private static StatisticsMenu instance;

    private StatisticsMenu() {
    }

    public static StatisticsMenu getInstance() {
        if (instance == null) {
            instance = new StatisticsMenu();
        }
        return instance;
    }

    public void statisticsMenuPrint() {
        System.out.println("1. 일별 통계");
        System.out.println("2. 주별 통계");
        System.out.println("3. 월별 통계");
        System.out.println("4. 연도별 통계");
        System.out.println("0. 나가기");
        System.out.print(">> ");
        String number = sc.nextLine();
        System.out.println();

        while (!number.equals("1") && !number.equals("2") && !number.equals("3") && !number.equals("4") && !number.equals("0")) {
            System.out.println("다시 입력하세요.");
            System.out.print(">> ");
            number = sc.nextLine();
            System.out.println();
        }

        if (number.equals("1")) { // 일별 통계
            inputDateMenu.inputYearMonthDay();
            reportService.showStatistics(reportService.getDayReports(inputDateMenu.year, inputDateMenu.month, inputDateMenu.day));
            System.out.println();
        } else if (number.equals("2")) { // 주별 통계
            System.out.println("서비스 예정입니다.");
            System.out.println();
        } else if (number.equals("3")) { // 월별 통계
            inputDateMenu.inputYearMonth();
            reportService.showStatistics(reportService.getMonthReports(inputDateMenu.year, inputDateMenu.month));
            System.out.println();
        } else if (number.equals("4")) { // 연도별 통계
            inputDateMenu.inputYear();
            reportService.showStatistics(reportService.getYearReports(inputDateMenu.year));
            System.out.println();
        } else {
            MainMenu.getInstance().mainMenuPrint();
            System.out.println();
        }
        StatisticsMenu.getInstance().statisticsMenuPrint();
        System.out.println();
    }
}