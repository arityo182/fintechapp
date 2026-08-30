package com.fintechapp.auth_users.services;

import com.fintechapp.auth_users.repo.PasswordResetCodeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * Komponen utilitas untuk menghasilkan kode alfanumerik acak yang unik dan aman.
 * Digunakan terutama untuk token/kode reset password.
 *
 * @author Ari
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class CodeGenerator {

    private final PasswordResetCodeRepo passwordResetCodeRepo;

    private static final String ALPHA_NUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 5;

    /**
     * Menghasilkan kode acak unik yang belum terdaftar di database.
     *
     * @return String kode alfanumerik unik dengan panjang yang telah ditentukan
     */
    public String generateUniqueCode() {
        String code;

        do {
            code = generateRandomCode();
        } while (passwordResetCodeRepo.findByCode(code).isPresent());
        return code;
    }

    /**
     * Menghasilkan kombinasi string acak dari karakter alfanumerik.
     *
     * @return String acak sepanjang {@value #CODE_LENGTH} karakter
     */
    private String generateRandomCode() {
        StringBuilder sb = new StringBuilder(CODE_LENGTH);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(ALPHA_NUMERIC.length());
            sb.append(ALPHA_NUMERIC.charAt(index));
        }
        return sb.toString();
    }
}
