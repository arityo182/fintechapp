package com.fintechapp.auth_users.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) untuk respons autentikasi login yang berhasil.
 * Berisi JWT token akses dan daftar role pengguna.
 *
 * @author Ari
 * @since 1.0.0
 */
@Data
@Builder
public class LoginResponse {

    private String token;
    private List<String> roles;
}
