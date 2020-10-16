package com.company.accountbook.dto;

public class Report {
    protected int reportId;
    protected String content;
    protected int price;
    protected String memo;
    protected static String accountBookName;
    protected int year;
    protected int month;
    protected int day;

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

    public String getContent() {
        return content;
    }

    public static void setAccountBookName(String accountBookName) {
        Report.accountBookName = accountBookName;
    }

    public static String getAccountBookName() {
        return accountBookName;
    }
}
