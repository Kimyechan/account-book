package com.company.accountbook;

import com.company.accountbook.service.checkaccountbook.MonthCheckAccountBook;
import com.company.accountbook.util.Menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MonthCheckAccountBook monthCheckAccountBook = new MonthCheckAccountBook();
        Scanner sc = new Scanner(System.in);
        Menu menu = Menu.getInstance();

        // 초기 화면 출력 (이번달 달력)
        monthCheckAccountBook.printCalendar();

        // 메인 메뉴 출력
        menu.mainMenuPrint();
//        ReportDAO reportDAO = new ReportDAO(new AccountBook("yechan"));
//        reportDAO.addReport("aaaaaa", 20000, "aaaa", LocalDate.of(2020, 10, 10));
//
//        List<Report> reports = reportDAO.getMonthReport(2020, 10);
//        for(Report report : reports) {
//            System.out.println(report.getContent());
//        }
    }
}