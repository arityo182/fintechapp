package com.fintechapp.exceptions;

/**
 * Pengecualian yang dilemparkan ketika parameter transaksi melanggar aturan bisnis keuangan.
 *
 * @author Ari
 * @since 1.0.0
 */
public class InvalidTransactionException extends RuntimeException {

    /**
     * Konstruktor dengan pesan rincian kesalahan transaksi.
     *
     * @param error detail pesan kesalahan
     */
    public InvalidTransactionException(String error) {
        super(error);
    }
}
