package com.company.accountbook.util;

import java.util.Scanner;

public class InputDateMenu {
    Scanner sc = new Scanner(System.in);
    protected int year;
    protected int month;
    protected int day;


    // 싱글톤
    private static InputDateMenu instance;

    private InputDateMenu() {
    }

    public static InputDateMenu getInstance() {
        if (instance == null) {
            instance = new InputDateMenu();
        }
        return instance;
    }

    public void inputYearMonthDay() {
        System.out.println("조회를 원하는 날을 입력하세요. ex) 2020-10-15");
        System.out.print(">> ");
        try {
            String[] date = sc.nextLine().split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
            day = Integer.parseInt(date[2]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            inputYearMonthDay();
        }
    }

    public void inputYearMonth() {
        System.out.println("조회를 원하는 달을 입력하세요. ex) 2020-10");
        System.out.print(">> ");
        try {
            String[] date = sc.nextLine().split("-");
            year = Integer.parseInt(date[0]);
            month = Integer.parseInt(date[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            inputYearMonth();
        }
    }

    public void inputYear() {
        System.out.println("조회를 원하는 연도를 입력하세요. ex) 2020-10-15");
        System.out.print(">> ");
        try {
            String[] date = sc.nextLine().split("-");
            year = Integer.parseInt(date[0]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("형식에 맞게 입력하세요.");
            inputYear();
        }
    }
}