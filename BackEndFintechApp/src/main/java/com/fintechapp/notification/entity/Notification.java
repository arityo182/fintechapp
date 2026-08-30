package com.fintechapp.notification.entity;

import java.time.LocalDateTime;

import com.fintechapp.auth_users.entity.User;
import com.fintechapp.enums.NotificationType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entitas JPA untuk mencatat riwayat pesan notifikasi yang dikirimkan ke pengguna.
 *
 * @author Ari
 * @since 1.0.0
 */
@Entity
@Table(name = "notifications")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;
    private String recipient;
    private String body;

    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private final LocalDateTime createdAt = LocalDateTime.now();
}
