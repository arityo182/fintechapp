package com.fintechapp.role.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fintechapp.res.Response;
import com.fintechapp.role.entity.Role;
import com.fintechapp.role.services.RoleService;

import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * REST Controller untuk manajemen data role pengguna.
 * Mengatur operasi penambahan, pembaruan, penampilan, dan penghapusan role (khusus ADMIN).
 *
 * @author Ari
 * @since 1.0.0
 */
@RestController
@RequestMapping
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ADMIN')")
public class RoleController {

    private final RoleService roleService;

    /**
     * Endpoint untuk membuat role baru.
     *
     * @param roleRequest request body berisi data role
     * @return {@link ResponseEntity} berisi status dan data role yang dibuat
     */
    @PostMapping
    public ResponseEntity<Response<Role>> createRole(@RequestBody Role roleRequest) {
        return ResponseEntity.ok(roleService.createRole(roleRequest));
    }

    /**
     * Endpoint untuk mengubah data role yang sudah ada.
     *
     * @param roleRequest request body berisi data role terbaru beserta ID
     * @return {@link ResponseEntity} berisi status dan data role yang diperbarui
     */
    @PutMapping
    public ResponseEntity<Response<Role>> updateRole(@RequestBody Role roleRequest) {
        return ResponseEntity.ok(roleService.updateRole(roleRequest));
    }

    /**
     * Endpoint untuk mengambil seluruh daftar role yang ada di sistem.
     *
     * @return {@link ResponseEntity} berisi list seluruh role
     */
    @GetMapping
    public ResponseEntity<Response<List<Role>>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    /**
     * Endpoint untuk menghapus role berdasarkan ID.
     *
     * @param id ID role yang akan dihapus
     * @return {@link ResponseEntity} berisi status hasil penghapusan
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.deleteRole(id));
    }
}
