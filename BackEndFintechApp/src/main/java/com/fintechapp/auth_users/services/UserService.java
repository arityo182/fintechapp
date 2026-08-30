package com.fintechapp.auth_users.services;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.fintechapp.auth_users.dto.UpdatePasswordRequest;
import com.fintechapp.auth_users.dto.UserDTO;
import com.fintechapp.auth_users.entity.User;
import com.fintechapp.res.Response;

/**
 * Service interface untuk manajemen data profil pengguna dan manipulasi akun.
 *
 * @author Ari
 * @since 1.0.0
 */
public interface UserService {

    /**
     * Mendapatkan entitas pengguna yang saat ini sedang login melalui Security Context.
     *
     * @return entitas {@link User} yang sedang aktif
     */
    User getCurrentLoggedInUser();

    /**
     * Mengambil profil pengguna yang sedang login beserta rekening yang terhubung.
     *
     * @return data profil pengguna dalam format {@link UserDTO}
     */
    Response<UserDTO> getMyProfile();

    /**
     * Mengambil daftar seluruh pengguna secara terpaginasi (khusus admin).
     *
     * @param page nomor halaman
     * @param size jumlah data per halaman
     * @return halaman data daftar {@link UserDTO}
     */
    Response<Page<UserDTO>> getAllUsers(int page, int size);

    /**
     * Memperbarui password pengguna saat ini dengan memverifikasi password lama.
     *
     * @param updatePasswordRequest data password lama dan password baru
     * @return respons status pembaruan password
     */
    Response<?> updatePassword(UpdatePasswordRequest updatePasswordRequest);

    /**
     * Mengunggah foto profil pengguna ke storage lokal atau server.
     *
     * @param file file gambar yang diunggah
     * @return respons status hasil unggahan foto profil
     */
    Response<?> uploadProfilePicture(MultipartFile file);

    /**
     * Mengunggah foto profil pengguna ke storage AWS S3.
     *
     * @param file file gambar yang diunggah
     * @return respons status hasil unggahan ke AWS S3
     */
    Response<?> uploadProfilePictureTo53(MultipartFile file);
}
