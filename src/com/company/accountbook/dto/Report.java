package com.company.accountbook.dto;

public class Report {
    private static String accountBookName;
    private int reportId;
    private boolean isIncome;
    private String paymentMethod;
    private String category;
    private int price;
    private String content;
    private int year;
    private int month;
    private int day;

    public Report() {
    }

    public Report(boolean isIncome, String category, int price, String content, String accountBookName, int year, int month, int day) {
        this.category = category;
        this.isIncome = isIncome;
        this.price = price;
        this.content = content;
        this.accountBookName = accountBookName;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Report(boolean isIncome, String paymentMethod, String category, int price, String content, String accountBookName, int year, int month, int day) {
        this.isIncome = isIncome;
        this.paymentMethod = paymentMethod;
        this.category = category;
        this.price = price;
        this.content = content;
        this.accountBookName = accountBookName;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public Report(int reportId, boolean isIncome, String paymentMethod, String category, int price, String content, String accountBookName, int year, int month, int day) {
        this.reportId = reportId;
        this.isIncome = isIncome;
        this.paymentMethod = paymentMethod;
        this.category = category;
        this.price = price;
        this.content = content;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        if (isIncome) {
            return year + "년 " + month + "월 " + day + "일" + "\n" +
                    "카테고리: " + category + "\n" +
                    "수입 내역: " + content + "\n" +
                    "금액: " + price + "\n"
                    ;
        } else {
            return year + "년 " + month + "월 " + day + "일" + "\n" +
                    "카테고리: " + category + "\n" +
                    "지출 내역: " + content + "\n" +
                    "금액: " + price + "\n" +
                    "지불 수단: " + paymentMethod + "\n"
                    ;
        }
    }
}
