package com.company.accountbook;

import com.company.accountbook.dao.ReportDAO;
import com.company.accountbook.dto.AccountBook;
import com.company.accountbook.util.MainMenu;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 초기 화면 출력 (이번달 달력)

        // 메인 메뉴 출력
        MainMenu.getInstance().mainMenuPrint();
    }
}