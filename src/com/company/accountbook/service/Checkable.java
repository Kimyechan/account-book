package com.company.accountbook.service;

import com.company.accountbook.dto.Report;

import java.util.List;

public interface Checkable {
    List<Report> checkAccount();
    void showStatics();
    void printAccount();
}
