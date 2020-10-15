package com.company.accountbook.dto;

import com.company.accountbook.vo.ExpenseCategory;
import com.company.accountbook.vo.PayCategory;

public class Expense extends Report{
    ExpenseCategory category;
    PayCategory payMethod;
}