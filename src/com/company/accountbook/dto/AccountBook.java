package com.company.accountbook.dto;

public class AccountBook {
    private int bookId;
    private String bookName;
    private String password;

    public AccountBook() {
    }

    public AccountBook(String bookName) {
        this.bookName = bookName;
    }

    public AccountBook(String bookName, String password) {
        this.bookName = bookName;
        this.password = password;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return bookName;
    }
}
