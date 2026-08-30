package com.fintechapp.enums;

/**
 * Enumerasi yang mendefinisikan status aktif/tidaknya rekening pengguna.
 *
 * @author Ari
 * @since 1.0.0
 */
public enum AccountStatus {
    /** Rekening aktif dan dapat bertransaksi */
    ACTIVE,
    /** Rekening ditangguhkan sementara */
    SUSPENDED,
    /** Rekening telah ditutup */
    CLOSED
}
