package com.fintechapp.security;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Service untuk menangani pembuatan (generate), ekstraksi klaim, dan validasi
 * JSON Web Token (JWT).
 *
 * @author Ari
 * @since 1.0.0
 */
@Service
public class TokenService {

    @Value("${jwt.secret.string}")
    private String JWT_SECRETE;

    @Value("${jwt.expiration.time}")
    private long EXPIRATION_TIME;

    private SecretKey key;

    /**
     * Menginisialisasi SecretKey dari konfigurasi secret string setelah bean
     * dibuat.
     */
    @PostConstruct
    private void init() {
        byte[] keyByte = JWT_SECRETE.getBytes(StandardCharsets.UTF_8);
        this.key = new SecretKeySpec(keyByte, "HmacSHA256");
    }

    /**
     * Menghasilkan (generate) JWT token baru berdasarkan email pengguna.
     *
     * @param email email/subject pengguna yang akan dibuatkan token
     * @return string JWT token yang telah ditandatangani
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    /**
     * Mengekstrak username (email) yang tersimpan dalam payload subject JWT token.
     *
     * @param token string JWT token
     * @return username/email pengguna
     */
    public String getUsernameFromToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    /**
     * Mengekstrak username (email) dari JWT token (alias untuk
     * getUsernameFromToken).
     *
     * @param token string JWT token
     * @return username/email pengguna
     */
    public String getUserFromToken(String token) {
        return getUsernameFromToken(token);
    }

    /**
     * Mengekstrak claim tertentu dari JWT token menggunakan fungsi resolver.
     *
     * @param token          string JWT token
     * @param claimsFunction fungsi pemeta untuk mengambil klaim tertentu
     * @param <T>            tipe data klaim yang dikembalikan
     * @return nilai claim hasil ekstraksi
     */
    private <T> T extractClaims(String token, Function<Claims, T> claimsFunction) {
        return claimsFunction.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }

    /**
     * Memvalidasi apakah JWT token cocok dengan data pengguna dan belum kadaluarsa.
     *
     * @param token       string JWT token
     * @param userDetails data detail pengguna dari Spring Security
     * @return true jika token valid dan milik pengguna yang bersangkutan, false
     *         jika tidak
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Memeriksa apakah masa berlaku (expiration time) JWT token telah lewat dari
     * waktu sekarang.
     *
     * @param token string JWT token
     * @return true jika token telah kadaluarsa, false jika masih berlaku
     */
    private boolean isTokenExpired(String token) {
        return extractClaims((token), Claims::getExpiration).before(new Date());
    }
}
