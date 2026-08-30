package com.fintechapp.role.services;

import java.util.List;

import com.fintechapp.res.Response;
import com.fintechapp.role.entity.Role;

/**
 * Service interface untuk pengelolaan data role dan hak akses dalam aplikasi.
 *
 * @author Ari
 * @since 1.0.0
 */
public interface RoleService {

    /**
     * Membuat data role baru di sistem.
     *
     * @param roleRequest data role yang akan disimpan
     * @return respons hasil penyimpanan role
     */
    Response<Role> createRole(Role roleRequest);

    /**
     * Memperbarui nama atau data role yang sudah ada.
     *
     * @param roleRequest data role terbaru beserta ID
     * @return respons hasil pembaruan role
     */
    Response<Role> updateRole(Role roleRequest);

    /**
     * Mengambil daftar seluruh role yang tersedia di sistem.
     *
     * @return respons memuat daftar role
     */
    Response<List<Role>> getAllRoles();

    /**
     * Menghapus role dari sistem berdasarkan ID.
     *
     * @param id ID role yang akan dihapus
     * @return respons status penghapusan role
     */
    Response<?> deleteRole(Long id);
}
