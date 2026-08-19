package com.fintechapp.account.services;

import java.util.List;

import com.fintechapp.account.dto.AccountDTO;
import com.fintechapp.account.entity.Account;
import com.fintechapp.auth_users.entity.User;
import com.fintechapp.enums.AccountType;
import com.fintechapp.res.Response;

public interface AccountService {

    Account createAccount(AccountType AccountType, User user);

    Response<List<AccountDTO>> getMyAccounts();

    Response<?> closeAccount(String accountNumber);
}
