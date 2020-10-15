package com.company.accountbook;

import com.company.accountbook.service.MonthCheckAccountBook;
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
    }
}