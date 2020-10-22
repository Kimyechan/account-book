package com.company.accountbook.util;

import com.company.accountbook.service.AccountBookService;
import com.company.accountbook.service.ReportService;

import java.util.Scanner;

public class AccountBookMenu {
    AccountBookService accountBookService = new AccountBookService();
    ReportService reportService = new ReportService();
    private String accountBookName;
    private String password;
    private String deleteCheck;

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

        System.out.println("1. 가계부 조회");
        System.out.println("2. 가계부 생성");
        System.out.println("3. 가계부 수정");
        System.out.println("4. 가계부 삭제");
        System.out.print(">> ");
        String number = sc.nextLine();
        System.out.println();

        while (!number.equals("1") && !number.equals("2") && !number.equals("3") && !number.equals("4")) {
            System.out.println("다시 입력하세요.");
            number = sc.nextLine();
            System.out.println();
        }

        if (number.equals("1")) {
            System.out.println("가계부 리스트: " + accountBookService.getAccountBooks());
            System.out.print("조회할 가계부 이름을 입력하세요: ");
            accountBookName = sc.nextLine();
            while (!accountBookService.checkExisting(accountBookName)) {
                System.out.print("다시 입력하세요: ");
                accountBookName = sc.nextLine();
            }
            reportService.setBookNameForReportList(accountBookName);
            System.out.println();
            System.out.print("비밀번호를 입력하세요: ");
            password = sc.nextLine();
            System.out.println();
            while (!accountBookService.checkAccessRight(accountBookName, password)) {
                System.out.print("다시 입력하세요: ");
                password = sc.nextLine();
            }
        } else if (number.equals("2")) { // 가계부 생성
            System.out.print("가계부 이름: ");
            String bookName = sc.nextLine();
            System.out.print("비밀번호: ");
            String pass = sc.nextLine();
            accountBookService.addAccountBook(bookName, pass);
            reportService.setBookNameForReportList(bookName);
            System.out.println();
            accountBookMenu();
        } else if (number.equals("3")){
            System.out.println("가계부 리스트: " + accountBookService.getAccountBooks());
            System.out.print("수정할 가계부 이름을 입력하세요: ");
            accountBookName = sc.nextLine();
            while (!accountBookService.checkExisting(accountBookName)) {
                System.out.print("다시 입력하세요: ");
                accountBookName = sc.nextLine();
            }

            reportService.setBookNameForReportList(accountBookName);
            System.out.println();
            System.out.print("현재 비밀번호를 입력하세요: ");
            password = sc.nextLine();
            System.out.println();
            while (!accountBookService.checkAccessRight(accountBookName, password)) {
                System.out.print("다시 입력하세요: ");
                password = sc.nextLine();
                System.out.println();
            }
            System.out.println("1. 이름 변경");
            System.out.println("2. 비밀번호 변경");
            System.out.print(">> ");
            number = sc.nextLine();
            System.out.println();

            while (!number.equals("1") && !number.equals("2")) {
                System.out.print("다시 입력하세요: ");
                number = sc.nextLine();
                System.out.println();
            }

            if (number.equals("1")) {
                System.out.print("새로운 가계부 이름을 입력하세요: ");
                accountBookName = sc.nextLine();
                System.out.println();
                while (accountBookService.checkExisting(accountBookName)) {
                    System.out.println("이미 존재하는 가계부 이름입니다.");
                    System.out.print("다시 입력하세요: ");
                    accountBookName = sc.nextLine();
                    System.out.println();
                }
                System.out.print("한번 더 입력하세요: ");
                String accountBookName2 = sc.nextLine();
                while (!accountBookName.equals(accountBookName2)) {
                    accountBookName = "";
                    System.out.println("잘못 입력 되었습니다.");
                    System.out.println();
                    System.out.print("새로운 가계부 이름을 입력하세요: ");
                    accountBookName = sc.nextLine();
                    System.out.println();
                    System.out.print("가계부 이름을 한번 더 입력하세요: ");
                    accountBookName2 = sc.nextLine();
                }
                System.out.println();
                System.out.println("*** 가계부 이름 변경이 완료되었습니다 ***");
                accountBookService.updateAccountBook(accountBookName, accountBookName, password);
                System.out.println();
            } else {
                System.out.print("변경할 비밀번호를 입력하세요: ");
                password = sc.nextLine();
                System.out.println();
                System.out.print("한번 더 입력하세요: ");
                String password2 = sc.nextLine();
                while (!password.equals(password2)) {
                    password = "";
                    System.out.println("비밀번호가 틀렸습니다.");
                    System.out.println();
                    System.out.print("비밀번호를 입력하세요: ");
                    password = sc.nextLine();
                    System.out.println();
                    System.out.print("비밀번호를 한번 더 입력하세요: ");
                    password2 = sc.nextLine();
                }
                System.out.println();
                System.out.println("*** 비밀번호 변경이 완료되었습니다 ***");
                accountBookService.updateAccountBook(accountBookName, accountBookName, password);
                System.out.println();
            }
        } else {
            deleteCheck ="";
            System.out.println("가계부 리스트: " + accountBookService.getAccountBooks());
            System.out.print("삭제할 가계부 이름을 입력하세요: ");
            accountBookName = sc.nextLine();
            while (!accountBookService.checkExisting(accountBookName)) {
                System.out.print("다시 입력하세요: ");
                accountBookName = sc.nextLine();
            }
            reportService.setBookNameForReportList(accountBookName);
            System.out.println();
            System.out.print("비밀번호를 입력하세요: ");
            password = sc.nextLine();
            System.out.println();
            while (!accountBookService.checkAccessRight(accountBookName, password)) {
                System.out.print("다시 입력하세요: ");
                password = sc.nextLine();
            }
            System.out.println("정말로 삭제 하시겠습니까? 삭제를 원하시면 yes, 취소는 아무키나 입력하세요.");
            deleteCheck = sc.nextLine();
            System.out.println();
            if(deleteCheck.equals("yes")) {
                accountBookService.deleteAccountBook(accountBookName);
            }
            System.out.println("*** 가계부 삭제가 완료되었습니다 ***");
            System.out.println();
            accountBookMenu();
        }
        MainMenu.getInstance().mainMenuPrint();
    }
}
