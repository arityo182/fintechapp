package com.fintechapp.auth_users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

/**
 * Data Transfer Object (DTO) untuk pendaftaran pengguna baru.
 * Memuat data profil dasar, kredensial, dan daftar role yang diminta.
 *
 * @author Ari
 * @since 1.0.0
 */
@Data
public class RegistrationRequest {

    @NotBlank(message = "FirstName is required")
    private String firstName;

    private String lastName;
    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    private List<String> roles;

    @NotBlank(message = "Password is required")
    private String password;
}
