package com.fintechapp.enums;

/**
 * Enumerasi status hasil pemrosesan transaksi finansial.
 *
 * @author Ari
 * @since 1.0.0
 */
public enum TransactionStatus {
    /** Transaksi berhasil diproses */
    SUCCESS,
    /** Transaksi gagal */
    FAILED,
    /** Transaksi sedang dalam proses/menunggu */
    PENDING
}
