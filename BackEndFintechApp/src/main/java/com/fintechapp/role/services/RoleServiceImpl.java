package com.fintechapp.role.services;

import com.fintechapp.exceptions.BadRequestException;
import com.fintechapp.exceptions.NotFoundException;
import com.fintechapp.res.Response;
import com.fintechapp.role.entity.Role;
import com.fintechapp.role.repo.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementasi layanan bisnis untuk pengelolaan data role sistem.
 *
 * @author Ari
 * @since 1.0.0
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    /**
     * Membuat role baru jika nama role belum terdaftar.
     *
     * @param roleRequest data role yang akan dibuat
     * @return respons berisi entitas role yang berhasil disimpan
     * @throws BadRequestException jika nama role sudah ada sebelumnya
     */
    @Override
    public Response<Role> createRole(Role roleRequest) {

        if (roleRepo.findByName(roleRequest.getName()).isPresent()) {
            throw new BadRequestException("Role already exists");
        }

        Role saveedRole = roleRepo.save(roleRequest);

        return Response.<Role>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Role saved successfully")
                .data(saveedRole)
                .build();
    }

    /**
     * Memperbarui role yang telah ada berdasarkan ID.
     *
     * @param roleRequest data role dengan ID yang ingin diubah
     * @return respons berisi data role hasil pembaruan
     * @throws NotFoundException jika ID role tidak ditemukan
     */
    @Override
    public Response<Role> updateRole(Role roleRequest) {
        Role role = roleRepo.findById(roleRequest.getId())
                .orElseThrow(() -> new NotFoundException("Role not found"));

        role.setName(roleRequest.getName());

        Role updatedRole = roleRepo.save(role);

        return Response.<Role>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Role updated successfully")
                .data(updatedRole)
                .build();
    }

    /**
     * Mengambil semua daftar role yang tersimpan di basis data.
     *
     * @return respons berisi daftar entitas role
     */
    @Override
    public Response<List<Role>> getAllRoles() {

        List<Role> roles = roleRepo.findAll();

        return Response.<List<Role>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Roles retreived successfully")
                .data(roles)
                .build();
    }

    /**
     * Menghapus role dari sistem berdasarkan ID.
     *
     * @param id ID role yang akan dihapus
     * @return respons status penghapusan role
     * @throws NotFoundException jika ID role tidak ditemukan
     */
    @Override
    public Response<?> deleteRole(Long id) {
        if (!roleRepo.existsById(id)) {
            throw new NotFoundException("Role Not Found");
        }
        roleRepo.deleteById(id);

        return Response.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Role deleted successfully")
                .build();
    }
}
