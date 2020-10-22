package com.company.accountbook.util;

import com.company.accountbook.service.ReportService;

import java.util.Scanner;

public class DeleteReportMenu {
    ReportService reportService = new ReportService();
    InputDateMenu inputDateMenu = InputDateMenu.getInstance();
    Scanner sc = new Scanner(System.in);
    protected int year;
    protected int month;
    protected int day;


    // 싱글톤
    private static DeleteReportMenu instance;

    private DeleteReportMenu() {
    }

    public static DeleteReportMenu getInstance() {
        if (instance == null) {
            instance = new DeleteReportMenu();
        }
        return instance;
    }

    public void showMonthReport() {
        showCalender();
        CheckMenu.getInstance().dayCheckReport();
    }

    public void showCalender() {
        System.out.println("조회를 원하는 달을 입력하세요. ex) 2020-10");
        System.out.print(">> ");
        try {
            String[] date = sc.nextLine().split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
        }

        if (reportService.getMonthReports(year, month).isEmpty()) {
            System.out.println("내역 없음");
        } else {
            // 달력
            Calendar.getInstance().printCalendar(year, month, reportService.getMonthReports(year, month));
        }
    }
}