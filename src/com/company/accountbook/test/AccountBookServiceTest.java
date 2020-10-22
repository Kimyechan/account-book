package com.company.accountbook.test;

import com.company.accountbook.dto.AccountBook;
import com.company.accountbook.service.AccountBookService;
import com.company.accountbook.service.ReportService;

import java.util.List;

public class AccountBookServiceTest {
    public static void main(String[] args) {
        AccountBookService accountBookService = new AccountBookService();
        ReportService reportService = new ReportService();

        accountBookService.addAccountBook("yechan","1234");
        accountBookService.addAccountBook("jb", "1234");


        List<AccountBook> accountBooks = accountBookService.getAccountBooks();
        for(AccountBook accountBook : accountBooks) {
            System.out.println(accountBook.getBookName() + " : " + accountBook.getPassword());
        }

//        System.out.println(accountBookService.checkAccessRight("test1", "1234"));
//        System.out.println(accountBookService.checkExisting("test2"));

//        accountBookService.deleteAccountBook("yechan");
//        accountBookService.updateAccountBook("yechan", "yechanChanged", "1234");

    }
}
