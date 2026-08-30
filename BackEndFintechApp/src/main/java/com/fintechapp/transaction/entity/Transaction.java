package com.fintechapp.transaction.entity;

import com.fintechapp.account.entity.Account;
import com.fintechapp.enums.TransactionStatus;
import com.fintechapp.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entitas JPA untuk mencatat detail riwayat transaksi perbankan (Deposit, Penarikan, Transfer).
 *
 * @author Ari
 * @since 1.0.0
 */
@Entity
@Data
@Builder
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TransactionType transactionType;

    @Column(nullable = false)
    private LocalDateTime transactionDate = LocalDateTime.now();

    private String description;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    // Untuk jenis transaksi transfer
    private String sourceAccount;
    private String destinationAccount;
}
