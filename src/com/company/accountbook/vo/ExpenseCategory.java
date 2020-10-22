package com.company.accountbook.vo;

public enum ExpenseCategory{
    FOOD(1), CLOTH(2), TAX(3);
    private int num;

    ExpenseCategory(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }
}
