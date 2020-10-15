package com.company.accountbook.dto;

import java.util.List;

public class AccountBook {
    private String username;
    private String password;

    public AccountBook() {
    }

    public AccountBook(String username) {
        this.username = username;
    }

    public AccountBook(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
