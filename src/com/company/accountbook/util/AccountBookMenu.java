package com.company.accountbook.util;

import com.company.accountbook.service.AccountBookService;
import com.company.accountbook.service.ReportService;

import java.util.Scanner;

public class AccountBookMenu {
    AccountBookService accountBookService = new AccountBookService();
    ReportService reportService = new ReportService();
    private String accountBookName;
    private String password;

    // 싱글톤
    private static AccountBookMenu instance;

    private AccountBookMenu() {
    }

    public static AccountBookMenu getInstance() {
        if (instance == null) {
            instance = new AccountBookMenu();
        }
        return instance;
    }

    // 가계부 메뉴
    public void accountBookMenu() {
        Scanner sc = new Scanner(System.in);

        // 초기 화면 출력 (이번달 달력)
        System.out.println("1. 가계부 조회");
        System.out.println("2. 가계부 생성");
        System.out.print(">> ");
        String number = sc.nextLine();
        System.out.println();

        while (!number.equals("1") && !number.equals("2")) {
            System.out.println("다시 입력하세요.");
            number = sc.nextLine();
            System.out.println();
        }

        if (number.equals("1")) {
            System.out.println("가계부 리스트: " + accountBookService.getAccountBooks());
            System.out.print("선택할 가계부 이름을 입력하세요: ");
            accountBookName = sc.nextLine();
            while(!accountBookService.checkExisting(accountBookName)) {
                System.out.print("다시 입력하세요: ");
                accountBookName = sc.nextLine();
            }
            reportService.setBookNameForReportList(accountBookName);
            System.out.println();
            System.out.print("비밀번호를 입력하세요: ");
            password = sc.nextLine();
            System.out.println();
            while(!accountBookService.checkAccessRight(accountBookName, password)) {
                System.out.print("다시 입력하세요: ");
                password = sc.nextLine();
            }
        } else {
            System.out.print("가계부 이름: ");
            String bookName = sc.nextLine();
            System.out.print("비밀번호: ");
            String pass = sc.nextLine();
            accountBookService.addAccountBook(bookName, pass);
            reportService.setBookNameForReportList(bookName);
            System.out.println();
            accountBookMenu();
        }
    }
}
