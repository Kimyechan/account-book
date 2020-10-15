package com.company.accountbook;

import com.company.accountbook.dao.ReportDAO;
import com.company.accountbook.dto.AccountBook;
import com.company.accountbook.service.AccountBookService;
import com.company.accountbook.util.MainMenu;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountBookService accountBookService = new AccountBookService();

        // 초기 화면 출력 (이번달 달력)
        System.out.println("1. 가계부 조회");
        System.out.println("2. 가계부 추가");
        System.out.print(">> ");
        String number = sc.nextLine();

        while (!number.equals("1") && !number.equals("2")) {
            System.out.println("다시 입력하세요.");
            number = sc.nextLine();
            System.out.println();
        }

        if (number.equals("1")) {
            System.out.println(accountBookService.getAccountBooks());
        } else {
            System.out.println("가계부 이름: ");
            String bookName = sc.nextLine();
            System.out.println("비밀번호: ");
            String pass = sc.nextLine();
            accountBookService.addAccountBook(bookName, pass);
        }


        // 메인 메뉴 출력
        MainMenu.getInstance().mainMenuPrint();
    }
}