package com.fintechapp.exceptions;

/**
 * Pengecualian yang dilemparkan ketika resource atau entitas data yang dicari tidak ditemukan (HTTP 404).
 *
 * @author Ari
 * @since 1.0.0
 */
public class NotFoundException extends RuntimeException {

    /**
     * Konstruktor dengan rincian pesan entitas yang tidak ditemukan.
     *
     * @param error pesan error detail
     */
    public NotFoundException(String error) {
        super(error);
    }
}
