package com.fintechapp.exceptions;

/**
 * Pengecualian (exception) untuk permintaan HTTP dengan parameter atau data yang tidak valid (HTTP 400).
 *
 * @author Ari
 * @since 1.0.0
 */
public class BadRequestException extends RuntimeException {

    /**
     * Konstruktor dengan pesan kesalahan.
     *
     * @param error rincian pesan error
     */
    public BadRequestException(String error) {
        super(error);
    }
}
