package com.fintechapp.role.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entitas JPA yang merepresentasikan role/otoritas hak akses pengguna (misal CUSTOMER, ADMIN, AUDITOR).
 *
 * @author Ari
 * @since 1.0.0
 */
@Entity
@Data
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "Role Name is required")
    private String name; // Nama role, misal: CUSTOMER, AUDITOR, ADMIN
}
