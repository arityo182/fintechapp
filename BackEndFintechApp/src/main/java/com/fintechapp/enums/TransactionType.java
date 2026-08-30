package com.fintechapp.enums;

/**
 * Enumerasi tipe operasional transaksi finansial.
 *
 * @author Ari
 * @since 1.0.0
 */
public enum TransactionType {
    /** Penyetoran dana ke rekening */
    DEPOSIT,
    /** Penarikan dana dari rekening */
    WITHDRAWAL,
    /** Pengiriman dana antar rekening */
    TRANSFER
}
