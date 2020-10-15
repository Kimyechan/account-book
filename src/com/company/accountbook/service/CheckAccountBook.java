package com.company.accountbook.service;

import com.company.accountbook.dto.Report;

import java.util.List;

public interface CheckAccountBook {
    List<Report> checkReport(int year, int month, int day);

    void showStatics();

    void printAccount();
}