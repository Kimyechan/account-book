package com.company.accountbook.dto;

public class Report {
    private static String accountBookName;
    private int reportId;
    private boolean isIncome;
    private String paymentMethod;
    private String content;
    private int price;
    private String memo;
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

    public Report(boolean isIncome, String paymentMethod, String content, int price, String memo, String accountBookName, int year, int month, int day) {
        this.isIncome = isIncome;
        this.paymentMethod = paymentMethod;
        this.content = content;
        this.price = price;
        this.memo = memo;
        this.accountBookName = accountBookName;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Report(int reportId, boolean isIncome, String paymentMethod, String content, int price, String memo, String accountBookName, int year, int month, int day) {
        this.reportId = reportId;
        this.paymentMethod = paymentMethod;
        this.content = content;
        this.price = price;
        this.memo = memo;
        this.accountBookName = accountBookName;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public static void setAccountBookName(String accountBookName) {
        Report.accountBookName = accountBookName;
    }

    public static String getAccountBookName() {
        return accountBookName;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", isIncome=" + isIncome +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", memo='" + memo + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
