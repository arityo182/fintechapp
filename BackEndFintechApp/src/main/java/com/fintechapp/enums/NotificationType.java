package com.fintechapp.enums;

/**
 * Enumerasi jenis saluran media pengiriman pesan notifikasi kepada pengguna.
 *
 * @author Ari
 * @since 1.0.0
 */
public enum NotificationType {
    /** Notifikasi melalui surat elektronik (Email) */
    EMAIL,
    /** Notifikasi melalui pesan singkat (SMS) */
    SMS,
    /** Notifikasi melalui dorongan aplikasi (Push Notification) */
    PUSH
}
