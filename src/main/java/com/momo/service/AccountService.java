package com.momo.service;

import com.momo.domain.entity.Account;
import com.momo.domain.vo.Money;

public final class AccountService {
    private Account account;

    public AccountService() {
        this.account = new Account(1, Money.ZERO);
    }

    public Money addFund(Money amount) {
        account = account.credit(amount);
        return account.balance();
    }

    public Money deduct(Money amount) {
        account = account.debit(amount);
        return account.balance();
    }

    public Money balance() {
        return account.balance();
    }
}
