package com.company.accountbook.util;

import com.company.accountbook.service.ReportService;

import java.util.Map;
import java.util.Scanner;

public class StatisticsMenu {
    Scanner sc = new Scanner(System.in);
    private int totalMoney;
    Map<String, Integer> expense;
    Map<String, Integer> income;
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

        switch (number) {
            case "1":  // 일별 통계
                // 날짜 입력
                inputDateMenu.inputYearMonthDay();

                // 지출 리스트 가져옴
                expense = reportService.getDayExpenseStatics(inputDateMenu.year, inputDateMenu.month, inputDateMenu.day);
                // 수입 리스트 가져옴
                income = reportService.getDayIncomeStatics(inputDateMenu.year, inputDateMenu.month, inputDateMenu.day);

                // 지출 출력
                printStatistics(false, expense);
                System.out.println();
                // 수입 출력
                printStatistics(true, income);
                System.out.println();
                break;

            case "2":  // 주별 통계
                System.out.println("서비스 예정입니다.");
                System.out.println();
                break;

            case "3":  // 월별 통계
                inputDateMenu.inputYearMonth();
                // 지출 리스트 가져옴
                expense = reportService.getMonthExpenseStatics(inputDateMenu.year, inputDateMenu.month);
                // 수입 리스트 가져옴
                income = reportService.getMonthIncomeStatics(inputDateMenu.year, inputDateMenu.month);

                // 지출 출력
                printStatistics(false, expense);
                System.out.println();
                // 수입 출력
                printStatistics(true, income);
                System.out.println();
                break;

            case "4":  // 연도별 통계
                inputDateMenu.inputYear();
                // 지출 리스트 가져옴
                expense = reportService.getYearExpenseStatics(inputDateMenu.year);
                // 수입 리스트 가져옴
                income = reportService.getYearIncomeStatics(inputDateMenu.year);

                // 지출 출력
                printStatistics(false, expense);
                System.out.println();
                // 수입 출력
                printStatistics(true, income);
                System.out.println();
                break;

            default:
                MainMenu.getInstance().mainMenuPrint();
                System.out.println();
                break;
        }
        StatisticsMenu.getInstance().statisticsMenuPrint();
        System.out.println();
    }


    public void printStatistics(boolean isIncome,Map<String, Integer> reportList) {
        totalMoney = 0;

        if(isIncome) {
            System.out.println("지출");
        } else {
            System.out.println("수입");
        }
        if (reportList.isEmpty()) {
            System.out.println("- 내역 없음");
        } else {
            for (Map.Entry<String, Integer> entry : reportList.entrySet()) {
                totalMoney += entry.getValue();
            }
            for (Map.Entry<String, Integer> entry : reportList.entrySet()) {
                System.out.println("- " + entry.getKey() + ": " + entry.getValue() + " (" + ((float) entry.getValue() * 100 / (float) totalMoney) + " %)");
            }
            System.out.println();
            System.out.println("총 금액: " + totalMoney);
        }
    }
}