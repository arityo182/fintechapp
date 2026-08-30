package com.fintechapp.account.services;

import java.util.List;

import com.fintechapp.account.dto.AccountDTO;
import com.fintechapp.account.entity.Account;
import com.fintechapp.auth_users.entity.User;
import com.fintechapp.enums.AccountType;
import com.fintechapp.res.Response;

/**
 * Service interface untuk manajemen rekening keuangan nasabah (pembuatan, penutupan, dan informasi akun).
 *
 * @author Ari
 * @since 1.0.0
 */
public interface AccountService {

    /**
     * Membuat rekening baru untuk pengguna dengan nomor rekening unik otomatis.
     *
     * @param AccountType jenis rekening (misal SAVINGS atau CURRENT)
     * @param user entitas pengguna pemilik rekening
     * @return entitas {@link Account} yang telah dibuat dan disimpan
     */
    Account createAccount(AccountType AccountType, User user);

    /**
     * Mengambil semua rekening yang dimiliki oleh pengguna yang sedang login.
     *
     * @return respons memuat daftar {@link AccountDTO}
     */
    Response<List<AccountDTO>> getMyAccounts();

    /**
     * Menutup rekening tertentu jika saldo bernilai nol dan dimiliki oleh pengguna yang login.
     *
     * @param accountNumber nomor rekening yang akan ditutup
     * @return respons status hasil penutupan rekening
     */
    Response<?> closeAccount(String accountNumber);
}
