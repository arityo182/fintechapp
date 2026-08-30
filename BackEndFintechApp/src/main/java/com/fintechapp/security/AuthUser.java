package com.fintechapp.security;

import lombok.Builder;
import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

import com.fintechapp.auth_users.entity.User;

/**
 * Representasi Principal pengguna untuk Spring Security yang
 * mengimplementasikan {@link UserDetails}.
 * Membungkus entitas {@link User} domain model.
 *
 * @author Ari
 * @since 1.0.0
 */
@Builder
@Data
public class AuthUser implements UserDetails {

    private User user;

    /**
     * Mengambil daftar otoritas/role yang dimiliki oleh pengguna.
     *
     * @return koleksi objek {@link GrantedAuthority} dari role pengguna
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Mengambil password terenkripsi pengguna.
     *
     * @return hash password pengguna
     */
    @Override
    public @Nullable String getPassword() {
        return user.getPassword();
    }

    /**
     * Mengambil identitas username pengguna (menggunakan alamat email).
     *
     * @return alamat email pengguna sebagai username
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }
}
