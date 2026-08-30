package com.fintechapp.role.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fintechapp.role.entity.Role;

import java.util.Optional;

/**
 * Repositori JPA untuk operasi entitas {@link Role}.
 *
 * @author Ari
 * @since 1.0.0
 */
public interface RoleRepo extends JpaRepository<Role, Long> {

    /**
     * Mencari role berdasarkan nama role.
     *
     * @param name nama role (misal "ADMIN", "CUSTOMER")
     * @return {@link Optional} berisi Role jika ditemukan
     */
    Optional<Role> findByName(String name);
}
