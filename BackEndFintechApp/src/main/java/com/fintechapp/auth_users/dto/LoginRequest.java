package com.fintechapp.auth_users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Data Transfer Object (DTO) untuk permintaan login pengguna.
 * Memuat kredensial akun berupa email dan password.
 *
 * @author Ari
 * @since 1.0.0
 */
@Data
public class LoginRequest {

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    @NotBlank(message = "Password is required")
    private String password;
}
