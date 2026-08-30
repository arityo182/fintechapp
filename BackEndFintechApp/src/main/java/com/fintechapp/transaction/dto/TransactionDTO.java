package com.fintechapp.transaction.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.transaction.TransactionStatus;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fintechapp.account.dto.AccountDTO;
import com.fintechapp.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) untuk menampilkan data riwayat transaksi nasabah.
 *
 * @author Ari
 * @since 1.0.0
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private Long id;
    private BigDecimal amount;
    private TransactionType transactionType;
    private LocalDateTime transactionDate;
    private String description;
    private TransactionStatus status;

    @JsonBackReference // Diabaikan dari serialisasi untuk menghindari referensi sirkular ke AccountDTO
    private AccountDTO account;

    // Properti khusus transfer dana
    private String sourceAccount;
    private String destinationAccount;
}
