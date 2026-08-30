package com.fintechapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fintechapp.res.Response;

/**
 * Controller Advice terpusat untuk menangani dan memformat seluruh exception yang terjadi dalam aplikasi.
 *
 * @author Ari
 * @since 1.0.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Menangani seluruh exception umum yang tidak tertangani secara spesifik (HTTP 500).
     *
     * @param ex exception yang terjadi
     * @return respons error terstruktur dengan kode status 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response<?>> handleAllUnknownExceptions(Exception ex) {
        Response<?> response = Response.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Menangani exception ketika data entitas tidak ditemukan di database (HTTP 404).
     *
     * @param ex exception {@link NotFoundException}
     * @return respons error terstruktur dengan kode status 404
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Response<?>> handleNotFoundException(NotFoundException ex) {

        Response<?> response = Response.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Menangani kondisi ketika saldo rekening tidak mencukupi untuk melakukan transaksi (HTTP 400).
     *
     * @param ex exception {@link InsufficientBalanceException}
     * @return respons error terstruktur dengan kode status 400
     */
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<Response<?>> handleInsufficientBalance(InsufficientBalanceException ex) {

        Response<?> response = Response.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Menangani transaksi yang tidak valid secara bisnis (HTTP 400).
     *
     * @param ex exception {@link InvalidTransactionException}
     * @return respons error terstruktur dengan kode status 400
     */
    @ExceptionHandler(InvalidTransactionException.class)
    public ResponseEntity<Response<?>> handleInvalidTransaction(InvalidTransactionException ex) {

        Response<?> response = Response.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Menangani kesalahan permintaan data dari client (HTTP 400).
     *
     * @param ex exception {@link BadRequestException}
     * @return respons error terstruktur dengan kode status 400
     */
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Response<?>> handleBadRequestException(BadRequestException ex) {

        Response<?> response = Response.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
