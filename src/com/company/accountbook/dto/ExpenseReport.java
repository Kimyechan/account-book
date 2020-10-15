package com.company.accountbook.dto;

import com.company.accountbook.vo.ExpenseCategory;
import com.company.accountbook.vo.PayCategory;

public class ExpenseReport extends Report {
    ExpenseCategory category;
    PayCategory payMethod;

    @Override
    public String toString() {
        return "금액: " + price + "\n" +
                "카테고리: " + category + "\n" +
                "지불수단: " + payMethod +
                "내용: " + content + "\n" +
                "메모: " + memo + "\n";

    }
}