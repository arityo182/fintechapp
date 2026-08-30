package com.fintechapp.account.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintechapp.account.entity.Account;

/**
 * Repositori JPA untuk operasi persistensi entitas {@link Account}.
 *
 * @author Ari
 * @since 1.0.0
 */
public interface AccountRepo extends JpaRepository<Account, Long> {

    /**
     * Mencari rekening berdasarkan nomor rekening unik.
     *
     * @param accountNumber nomor rekening yang dicari
     * @return {@link Optional} berisi entitas Account jika ditemukan
     */
    Optional<Account> findByAccountNumber(String accountNumber);

    /**
     * Mengambil daftar seluruh rekening yang dimiliki oleh pengguna tertentu.
     *
     * @param userId ID pengguna pemilik rekening
     * @return daftar rekening milik pengguna
     */
    List<Account> findByUserId(Long userId);
}
