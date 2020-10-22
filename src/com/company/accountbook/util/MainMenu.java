package com.company.accountbook.util;

import com.company.accountbook.service.ReportService;

import java.time.LocalDate;
import java.util.Scanner;

public class MainMenu {
    Scanner sc = new Scanner(System.in);
    ReportService reportService = new ReportService();

    // 싱글톤
    private static MainMenu instance;

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    public void mainMenuPrint() {
        Calendar.getInstance().printCalendar(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), reportService.getMonthReports(LocalDate.now().getYear(), LocalDate.now().getMonthValue()));
        System.out.println("1. 조회");
        System.out.println("2. 가계부 내역 추가");
        System.out.println("3. 가계부 내역 삭제");
        System.out.println("4. 통계");
        System.out.println("0. 로그아웃");
        System.out.print(">> ");
        String number = sc.nextLine();
        System.out.println();

        while (!number.equals("1") && !number.equals("2") && !number.equals("3") && !number.equals("4") && !number.equals("0")) {
            System.out.println("다시 입력하세요.");
            number = sc.nextLine();
            System.out.println();
        }
        if (number.equals("1")) {
            CheckMenu.getInstance().checkMenuPrint();
        } else if (number.equals("2")) {
            InputReportMenu.getInstance().inputReportMenuPrint();
        } else if(number.equals("3")){
            DeleteReportMenu.getInstance().showMonthReport();
        } else if (number.equals("4")) {
            StatisticsMenu.getInstance().statisticsMenuPrint();
        } else {
            AccountBookMenu.getInstance().accountBookMenu();
        }
    }
}