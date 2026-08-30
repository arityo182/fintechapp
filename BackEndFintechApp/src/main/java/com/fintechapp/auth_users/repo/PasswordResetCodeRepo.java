package com.fintechapp.auth_users.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintechapp.auth_users.entity.PasswordResetCode;

/**
 * Repositori JPA untuk operasi persistensi entitas {@link PasswordResetCode}.
 *
 * @author Ari
 * @since 1.0.0
 */
public interface PasswordResetCodeRepo extends JpaRepository<PasswordResetCode, Long> {

    /**
     * Mencari data reset code berdasarkan kode acak.
     *
     * @param code kode reset password
     * @return {@link Optional} berisi entitas PasswordResetCode jika ditemukan
     */
    Optional<PasswordResetCode> findByCode(String code);

    /**
     * Menghapus semua kode reset milik pengguna berdasarkan ID pengguna.
     *
     * @param userId ID pengguna pemilik kode
     */
    void deleteByUserId(Long userId);
}
