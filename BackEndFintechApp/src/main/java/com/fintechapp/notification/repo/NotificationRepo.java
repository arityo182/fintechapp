package com.fintechapp.notification.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintechapp.notification.entity.Notification;

/**
 * Repositori JPA untuk operasi penyimpanan dan pengambilan entitas {@link Notification}.
 *
 * @author Ari
 * @since 1.0.0
 */
public interface NotificationRepo extends JpaRepository<Notification, Long> {

}
