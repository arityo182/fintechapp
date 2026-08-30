package com.fintechapp.notification.services;

import com.fintechapp.notification.dto.NotificationDTO;
import com.fintechapp.auth_users.entity.User;

/**
 * Service interface untuk pengiriman dan penyimpanan riwayat notifikasi ke pengguna.
 *
 * @author Ari
 * @since 1.0.0
 */
public interface NotificationService {

    /**
     * Mengirimkan pesan email secara asinkron kepada pengguna.
     *
     * @param notification objek data notifikasi yang akan dikirimkan
     * @param user entitas pengguna penerima notifikasi
     */
    void sendEmail(NotificationDTO notification, User user);
}
