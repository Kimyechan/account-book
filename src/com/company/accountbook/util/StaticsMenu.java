package com.company.accountbook.util;

import java.util.Scanner;

public class StaticsMenu {
    Scanner sc = new Scanner(System.in);
    private String number;

    // 싱글톤
    private static StaticsMenu instance;

    private StaticsMenu() {
    }

    public static StaticsMenu getInstance() {
        if (instance == null) {
            instance = new StaticsMenu();
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
            dayStatics();
        } else if (number.equals("2")) {
            weekStatics();
        } else if (number.equals("3")) {
            monthStatics();
        } else if (number.equals("4")) {
            yearStatics();
        } else {
        }
    }

    public void dayStatics() {}
    public void weekStatics() {}
    public void monthStatics() {}
    public void yearStatics() {}
}