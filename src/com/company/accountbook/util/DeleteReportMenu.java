package com.company.accountbook.util;

import com.company.accountbook.dto.Report;
import com.company.accountbook.service.ReportService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DeleteReportMenu {
    ReportService reportService = new ReportService();
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
        int num = 1;
        HashMap<Integer, Report> reportHashMap = new HashMap<>();
        for (Report report : dayCheckReport()) {
            reportHashMap.put(num++, report);
        }
        System.out.println();
        System.out.println("수입, 지출 내역");
        for (Map.Entry<Integer, Report> entry : reportHashMap.entrySet()) {
            System.out.println(entry.getKey() + "\n" + entry.getValue());
        }
        System.out.println();

        Integer reportNum;

        while (true) {
            try {
                System.out.print("삭제할 내역의 번호를 입력하세요: ");
                reportNum = Integer.parseInt(sc.nextLine());
                if (!reportHashMap.containsKey(reportNum)) {
                    throw new Exception();
                }
                break;
            } catch (NumberFormatException ex) {
                System.out.println("잘못된 형식입니다. 다시 입력하세요.");
                System.out.println();
            } catch (Exception ex1) {
                System.out.println("없는 번호입니다.");
                System.out.println();
            }
        }

        reportService.deleteReport(reportHashMap.get(reportNum).getReportId());
        System.out.println();
        MainMenu.getInstance().mainMenuPrint();
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
        System.out.println();
        if (reportService.getMonthReports(year, month).isEmpty()) {
            System.out.println("내역 없음");
            System.out.println();
            showCalender();
        } else {
            Calendar.getInstance().printCalendar(year, month, reportService.getMonthReports(year, month));
        }
    }

    public List<Report> dayCheckReport() {
        System.out.println("조회를 원하는 날을 입력하세요. ex) 15");
        System.out.println("나가기는 0을 입력하세요.");
        System.out.print(">> ");
        try {
            day = Integer.parseInt(sc.nextLine());
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            dayCheckReport();
        }
        if(day == 0) {
            System.out.println();
            MainMenu.getInstance().mainMenuPrint();
        }
        if (reportService.getDayReports(year, month, day).isEmpty()) {
            System.out.println("내역 없음");
            dayCheckReport();
        } else {
            return reportService.getDayReports(year, month, day);
        }
        return null;
    }
}