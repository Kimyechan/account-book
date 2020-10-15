package com.company.accountbook.dto;

import java.time.LocalDate;

public class Report {
    private String content;
    private int price;
    private String memo;
    private String accountBookName;
    private int year;
    private int month;
    private int day;

    public Report() {
    }

    public Report(String content, int price, String memo, String accountBookName, int year, int month, int day) {
        this.content = content;
        this.price = price;
        this.memo = memo;
        this.accountBookName = accountBookName;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
