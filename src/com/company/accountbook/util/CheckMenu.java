package com.company.accountbook.util;

import com.company.accountbook.dao.ReportDAO;
import com.company.accountbook.dto.AccountBook;
import com.company.accountbook.service.ReportService;

import java.util.Scanner;

public class CheckMenu {
    ReportService reportService = new ReportService();
    Scanner sc = new Scanner(System.in);
    private String number;

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
        number = sc.nextLine();
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
    }

    public void dayCheckReport() {
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
            dayCheckReport();
        }
    }

    public void weekCheckReport() {

    }

    public void monthCheckReport() {
        System.out.println("조회를 원하는 달을 입력하세요. ex) 2020-10");
        System.out.print(">> ");
       try {
           String[] date = sc.nextLine().split("-");
           int year = Integer.parseInt(date[0]);
           int month = Integer.parseInt(date[1]);
           System.out.println(reportService.getMonthReports(year, month));
       } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
           System.out.println("형식에 맞게 입력하세요.");
           monthCheckReport();
       }
    }

    public void yearCheckReport() {
        System.out.println("조회를 원하는 연도를 입력하세요. ex) 2020");
        System.out.print(">> ");
        try {
            int year = sc.nextInt();
            System.out.println(reportService.getYearReports(year));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            yearCheckReport();
        }
    }
}