package com.fintechapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fintechapp.auth_users.entity.User;
import com.fintechapp.auth_users.repo.UserRepo;
import com.fintechapp.exceptions.NotFoundException;

/**
 * Implementasi kustom {@link UserDetailsService} dari Spring Security untuk
 * memuat data pengguna
 * dari database berdasarkan alamat email.
 *
 * @author Ari
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    /**
     * Memuat informasi pengguna dari basis data berdasarkan email (username).
     *
     * @param username alamat email pengguna yang login
     * @return representasi {@link UserDetails} yang membungkus entitas User
     * @throws UsernameNotFoundException jika pengguna tidak ditemukan
     * @throws NotFoundException         jika email pengguna tidak ada di database
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username).orElseThrow(() -> new NotFoundException("Email Not Found"));

        return AuthUser.builder().user(user).build();
    }
}
