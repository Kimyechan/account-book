package com.company.accountbook;

import com.company.accountbook.util.AccountBookMenu;

public class Main {
    public static void main(String[] args) {

        // 가계부 생성, 선택 메뉴
        AccountBookMenu.getInstance().accountBookMenu();
    }
}