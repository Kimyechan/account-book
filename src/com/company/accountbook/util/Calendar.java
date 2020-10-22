package com.company.accountbook.util;

import com.company.accountbook.dto.Report;
import com.company.accountbook.service.ReportService;

import java.util.ArrayList;
import java.util.List;

public class Calendar {
    ReportService reportService = new ReportService();
    private static Calendar instance;

    private Calendar() {
    }

    public static Calendar getInstance() {
        if (instance == null) {
            instance = new Calendar();
        }
        return instance;
    }

    public boolean isLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0) || year % 400 == 0;
    }

    public int dayOfMonth(int year, int month) {
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12:
                return 31;
            case 4, 6, 9, 11:
                return 30;
            case 2:
                if (isLeapYear(year)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                return -1;
        }
    }

    public int whatDayOfWeek(int year, int month, int day) {
        int sum = 6;
        for (int i = 1; i < year; i++) {
            if (isLeapYear(i)) {
                sum += 366;
            } else {
                sum += 365;
            }
        }
        for (int i = 1; i < month; i++) {
            sum += dayOfMonth(year, i);
        }
        sum += day;

        switch (sum % 7) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            case 5:
                return 6;
            case 6:
                return 7;
            default:
                return -1;
        }
    }

    public void printCalendar(int year, int month, List<Report> reportList) {

        System.out.printf("                   [%d년 %d월]\n", year, month);
        System.out.printf("%7s%7s%7s%7s%7s%7s%7s\n", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat");
        System.out.println("--------------------------------------------------");
        int day = 1;
        int expenseReportDay = 1;
        int incomeReportDay = 1;
        int count = whatDayOfWeek(year, month, 1) % 7;
        int count2 = count;
        int count3 = count;

        List<Integer> expenseReportDayList = new ArrayList<>();
        List<Integer> incomeReportDayList = new ArrayList<>();
        for (Report report : reportList) {
            if (report.isIncome()) {
                incomeReportDayList.add(report.getDay());
            } else {
                expenseReportDayList.add(report.getDay());
            }
        }

        loop:
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (count-- > 0) {
                    System.out.printf("%7s", "");
                } else {
                    if (dayOfMonth(year, month) < day) {
                        System.out.printf("%7s", "");
                    } else {
                        System.out.printf("%7s", day++);
                    }
                }
            }
            System.out.println();
            for (int j = 0; j < 7; j++) {
                if (count2-- > 0) {
                    System.out.printf("%7s", "");
                } else {
                    if (expenseReportDayList.contains(expenseReportDay++)) {
                        System.out.printf("%-7s", "  ex");
                    } else {
                        System.out.printf("%7s", "");
                    }
                }
            }
            System.out.println();
            for (int j = 0; j < 7; j++) {
                if (count3-- > 0) {
                    System.out.printf("%7s", "");
                } else {
                    if (incomeReportDayList.contains(incomeReportDay++)) {
                        System.out.printf("%-7s", "  in");
                    } else {
                        System.out.printf("%7s", "");
                    }
                    if (dayOfMonth(year, month) < incomeReportDay) {
                        break loop;
                    }
                }
            }
            System.out.println();
            System.out.println("--------------------------------------------------");
        }
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println();
    }
}