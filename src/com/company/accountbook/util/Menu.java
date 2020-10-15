package com.company.accountbook.util;

import java.util.Scanner;

public class Menu {
    Scanner sc = new Scanner(System.in);
    private String num;
    private String num1;
    private String num2;
    private String num3;

    // 싱글톤
    private static Menu instance;

    private Menu() {
    }

    public static Menu getInstance() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    public void mainMenuPrint() {
        System.out.println("1. 조회");
        System.out.println("2. 가계부 입력");
        System.out.println("3. 통계");
        System.out.print(">> ");
        num = sc.nextLine();
        System.out.println();
//        num = "1";
        while (!num.equals("1") && !num.equals("2") && !num.equals("3")) {
            System.out.println("다시 입력하세요.");
            num = sc.nextLine();
            System.out.println();
        }
        if (num.equals("1")) {
            checkMenuPrint();
        } else if (num.equals("2")) {
            inputReportMenuPrint();
        } else {
            staticMenuPrint();
        }
    }

    public void checkMenuPrint() {
        System.out.println("1. 일별 조회");
        System.out.println("2. 주별 조회");
        System.out.println("3. 월별 조회");
        System.out.println("4. 연도별 조회");
        System.out.println("0. 나가기");
        System.out.print(">> ");
        num1 = sc.nextLine();
        System.out.println();
//        num1 = "0";
        while (!num1.equals("1") && !num1.equals("2") && !num1.equals("3") && !num1.equals("4") && !num1.equals("0")) {
            System.out.println("다시 입력하세요.");
            num1 = sc.nextLine();
            System.out.println();
        }

        if (num1.equals("1")) {
        } else if (num1.equals("2")) {
        } else if (num1.equals("3")) {
        } else if (num1.equals("4")) {
        } else {
            mainMenuPrint();
        }
    }

    public void inputReportMenuPrint() {
        System.out.println("1. 지출 입력");
        System.out.println("2. 수입 입력");
        System.out.println("0. 나가기");
        System.out.print(">> ");
        num2 = sc.nextLine();
        System.out.println();

        while (!num2.equals("1") && !num2.equals("2") && !num2.equals("0")) {
            System.out.println("다시 입력하세요.");
            System.out.print(">> ");
            num2 = sc.nextLine();
            System.out.println();
        }

        if (num2.equals("1")) {
        } else if (num2.equals("2")) {
        } else if (num2.equals("3")) {
        } else {
            mainMenuPrint();
        }

    }

    public void staticMenuPrint() {
        System.out.println("1. 일별 통계");
        System.out.println("2. 주별 통계");
        System.out.println("3. 월별 통계");
        System.out.println("4. 연도별 통계");
        System.out.println("0. 나가기");
        System.out.print(">> ");
        num3 = sc.nextLine();
        System.out.println();

        while (!num3.equals("1") && !num3.equals("2") && !num3.equals("3") && !num3.equals("4") && !num3.equals("0")) {
            System.out.println("다시 입력하세요.");
            System.out.print(">> ");
            num3 = sc.nextLine();
            System.out.println();
        }

        if (num3.equals("1")) {
        } else if (num3.equals("2")) {
        } else if (num3.equals("3")) {
        } else if (num3.equals("4")) {
        } else {
            mainMenuPrint();
        }
    }
}