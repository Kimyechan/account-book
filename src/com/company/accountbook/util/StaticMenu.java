package com.company.accountbook.util;

import java.util.Scanner;

public class StaticMenu {
    Scanner sc = new Scanner(System.in);
    private String number;

    // 싱글톤
    private static StaticMenu instance;

    private StaticMenu() {
    }

    public static StaticMenu getInstance() {
        if (instance == null) {
            instance = new StaticMenu();
        }
        return instance;
    }

    public void staticMenuPrint() {
        System.out.println("1. 일별 통계");
        System.out.println("2. 주별 통계");
        System.out.println("3. 월별 통계");
        System.out.println("4. 연도별 통계");
        System.out.println("0. 나가기");
        System.out.print(">> ");
        number = sc.nextLine();
        System.out.println();

        while (!number.equals("1") && !number.equals("2") && !number.equals("3") && !number.equals("4") && !number.equals("0")) {
            System.out.println("다시 입력하세요.");
            System.out.print(">> ");
            number = sc.nextLine();
            System.out.println();
        }

        if (number.equals("1")) {
        } else if (number.equals("2")) {
        } else if (number.equals("3")) {
        } else if (number.equals("4")) {
        } else {
        }
    }
}