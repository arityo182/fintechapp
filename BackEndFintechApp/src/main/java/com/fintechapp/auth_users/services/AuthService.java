package com.fintechapp.auth_users.services;

import com.fintechapp.auth_users.dto.LoginRequest;
import com.fintechapp.auth_users.dto.LoginResponse;
import com.fintechapp.auth_users.dto.RegistrationRequest;
import com.fintechapp.auth_users.dto.ResetPasswordRequest;
import com.fintechapp.res.Response;

/**
 * Service interface untuk proses autentikasi, registrasi pengguna, dan manajemen reset password.
 *
 * @author Ari
 * @since 1.0.0
 */
public interface AuthService {

    /**
     * Mendaftarkan pengguna baru ke dalam sistem.
     *
     * @param request data permintaan registrasi pengguna
     * @return respons status hasil pendaftaran
     */
    Response<String> register(RegistrationRequest request);

    /**
     * Melakukan autentikasi kredensial pengguna dan mengembalikan token JWT.
     *
     * @param loginrequest data permintaan login (email & password)
     * @return respons yang memuat JWT token dan role pengguna
     */
    Response<LoginResponse> login(LoginRequest loginrequest);

    /**
     * Mengirimkan kode reset password ke email pengguna jika terdaftar.
     *
     * @param email alamat email pengguna
     * @return respons status pengiriman kode reset
     */
    Response<?> forgetPassword(String email);

    /**
     * Memperbarui kata sandi pengguna menggunakan kode reset yang valid.
     *
     * @param resetPasswordRequest data permintaan reset password beserta kode verifikasi
     * @return respons status keberhasilan perubahan kata sandi
     */
    Response<?> updatePasswordViaResetCode(ResetPasswordRequest resetPasswordRequest);
}
