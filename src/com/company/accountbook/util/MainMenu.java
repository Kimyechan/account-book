package com.company.accountbook.util;

import java.util.Scanner;

public class MainMenu {
    Scanner sc = new Scanner(System.in);
    private String number;

    // 싱글톤
    private static MainMenu instance;

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    public void mainMenuPrint() {
        System.out.println("1. 조회");
        System.out.println("2. 가계부 입력");
        System.out.println("3. 통계");
        System.out.print(">> ");
        number = sc.nextLine();
        System.out.println();

        while (!number.equals("1") && !number.equals("2") && !number.equals("3")) {
            System.out.println("다시 입력하세요.");
            number = sc.nextLine();
            System.out.println();
        }
        if (number.equals("1")) {
            CheckMenu.getInstance().checkMenuPrint();
        } else if (number.equals("2")) {
            InputReportMenu.getInstance().inputReportMenuPrint();
        } else {
            StaticMenu.getInstance().staticMenuPrint();
        }
    }
}