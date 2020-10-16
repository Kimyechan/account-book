package com.company.accountbook;

import com.company.accountbook.service.AccountBookService;
import com.company.accountbook.util.AccountBookMenu;
import com.company.accountbook.util.MainMenu;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // 가계부 생성, 선택 메뉴
        AccountBookMenu.getInstance().accountBookMenu();

        // 메인 메뉴 출력
        MainMenu.getInstance().mainMenuPrint();
    }
}