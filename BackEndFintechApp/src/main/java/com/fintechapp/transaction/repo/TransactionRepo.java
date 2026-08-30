package com.fintechapp.transaction.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fintechapp.transaction.entity.Transaction;

/**
 * Repositori JPA untuk mengakses dan mencari data riwayat entitas {@link Transaction}.
 *
 * @author Ari
 * @since 1.0.0
 */
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    /**
     * Mengambil riwayat transaksi rekening secara terpaginasi (baik sebagai sumber maupun penerima transfer).
     *
     * @param accountNumber nomor rekening yang dicari
     * @param pageable parameter paginasi dan sorting
     * @return halaman data transaksi
     */
    @Query("SELECT t FROM Transaction t WHERE t.account.accountNumber = :accountNumber " +
            "OR (t.transactionType = 'TRANSFER' AND t.destinationAccount = :accountNumber) " +
            "ORDER BY t.transactionDate DESC")
    Page<Transaction> findByAccount_AccountNumber(String accountNumber, Pageable pageable);

    /**
     * Mengambil seluruh riwayat transaksi rekening yang diurutkan dari transaksi terbaru.
     *
     * @param accountNumbeString nomor rekening yang dicari
     * @return daftar seluruh transaksi terkait
     */
    @Query("SELECT t FROM Transaction t WHERE t.account.accountNumber = :accountNumber " +
            "OR (t.transactionType = 'TRANSFER' AND t.destinationAccount = :accountNumber) " +
            "ORDER BY t.transactionDate DESC")
    List<Transaction> findByAccount_AccountNumber(String accountNumbeString);
}
