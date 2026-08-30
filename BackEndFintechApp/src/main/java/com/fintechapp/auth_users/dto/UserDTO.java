package com.fintechapp.auth_users.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.fintechapp.account.dto.AccountDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fintechapp.role.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Data Transfer Object (DTO) untuk menampilkan representasi data profil pengguna.
 *
 * @author Ari
 * @since 1.0.0
 */
@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

    private Long id;
    private String fistName;
    private String lastName;
    private String phoneNumber;
    private String email;

    @JsonIgnore
    private String password;

    private String profilePictureUrl;
    private boolean active;
    private List<Role> roles;

    @JsonManagedReference // Mencegah recursion loop dengan mengabaikan userDTO di dalam AccountDTO
    private List<AccountDTO> accounts;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
