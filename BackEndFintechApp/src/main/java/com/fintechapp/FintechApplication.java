package com.fintechapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Kelas utama dan titik masuk (entry point) aplikasi Fintech backend.
 * Menginisialisasi Spring Boot application context dan menjalankan embedded
 * server.
 *
 * @author Ari
 * @since 1.0.0
 */
@SpringBootApplication
public class FintechApplication {

    /**
     * Titik awal eksekusi program.
     *
     * @param args argumen baris perintah (command line arguments)
     */
    public static void main(String[] args) {
        SpringApplication.run(FintechApplication.class, args);
    }
}
