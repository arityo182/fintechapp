package com.fintechapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Kelas ini adalah titik masuk (entry point) aplikasi backend fintech.
 *
 * @SpringBootApplication adalah anotasi dari Spring Boot yang memberitahu
 * framework bahwa kelas ini berperan sebagai aplikasi utama. Dengan anotasi ini,
 * Spring Boot akan otomatis melakukan konfigurasi dasar, scan package,
 * dan menyiapkan aplikasi web/server untuk berjalan.
 *
 * Method main() adalah method yang pertama kali dipanggil saat aplikasi dijalankan.
 * SpringApplication.run(...) akan membuat konteks aplikasi Spring, menginisialisasi
 * bean, dan menjalankan server aplikasi (misalnya Tomcat untuk REST API).
 */
@SpringBootApplication
public class FintechApplication {

	public static void main(String[] args) {
		SpringApplication.run(FintechApplication.class, args);

	}

}
