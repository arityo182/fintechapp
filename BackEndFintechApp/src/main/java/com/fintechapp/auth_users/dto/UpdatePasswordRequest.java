package com.fintechapp.auth_users.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Data Transfer Object (DTO) untuk perubahan password pengguna yang sedang login.
 *
 * @author Ari
 * @since 1.0.0
 */
@Data
public class UpdatePasswordRequest {

    @NotBlank(message = "Old Password is required")
    private String oldPassword;

    @NotBlank(message = "New Password is required")
    private String newPassword;
}
