package com.fintechapp.auth_users.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintechapp.auth_users.entity.User;

/**
 * Repositori JPA untuk operasi basis data pada entitas {@link User}.
 *
 * @author Ari
 * @since 1.0.0
 */
public interface UserRepo extends JpaRepository<User, Long> {

    /**
     * Mencari pengguna berdasarkan alamat email.
     *
     * @param email alamat email pengguna
     * @return {@link Optional} berisi User jika ditemukan
     */
    Optional<User> findByEmail(String email);
}
