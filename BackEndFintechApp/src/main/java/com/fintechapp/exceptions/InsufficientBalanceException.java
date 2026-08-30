package com.fintechapp.exceptions;

/**
 * Pengecualian yang dilemparkan ketika saldo rekening tidak mencukupi untuk proses penarikan atau transfer dana.
 *
 * @author Ari
 * @since 1.0.0
 */
public class InsufficientBalanceException extends RuntimeException {

    /**
     * Konstruktor dengan rincian pesan kesalahan.
     *
     * @param error pesan error saldo tidak mencukupi
     */
    public InsufficientBalanceException(String error) {
        super(error);
    }
}
