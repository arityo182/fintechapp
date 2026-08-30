package com.fintechapp.auth_users.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Data Transfer Object (DTO) untuk permintaan reset password menggunakan kode verifikasi.
 *
 * @author Ari
 * @since 1.0.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResetPasswordRequest {

    private String email;
    private String code;
    private String newPassword;
}
