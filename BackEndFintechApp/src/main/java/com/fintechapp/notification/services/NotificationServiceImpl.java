package com.fintechapp.notification.services;

import java.nio.charset.StandardCharsets;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import com.fintechapp.auth_users.entity.User;
import com.fintechapp.enums.NotificationType;
import com.fintechapp.notification.dto.NotificationDTO;
import com.fintechapp.notification.entity.Notification;
import com.fintechapp.notification.repo.NotificationRepo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.context.Context;

/**
 * Implementasi layanan notifikasi untuk mengirimkan email HTML berbasis template Thymeleaf secara asinkron.
 *
 * @author Ari
 * @since 1.0.0
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepo notificationRepo;
    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    /**
     * Mengirimkan email asinkron menggunakan JavaMailSender dan Thymeleaf,
     * serta mencatat riwayat notifikasi ke dalam database.
     *
     * @param notificationDTO data notifikasi yang akan dikirim
     * @param user pengguna yang menerima email
     */
    @Override
    @Async
    public void sendEmail(NotificationDTO notificationDTO, User user) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setTo(notificationDTO.getRecipinet());
            helper.setSubject(notificationDTO.getSubject());

            // Gunakan template engine Thymeleaf jika templateName disertakan
            if (notificationDTO.getTemplateName() != null) {
                Context context = new Context();
                context.setVariables(notificationDTO.getTemplateVariables());
                String htmlContent = templateEngine.process(notificationDTO.getTemplateName(), context);
                helper.setText(htmlContent, true);
            } else {
                // Jika tidak menggunakan template, kirim body teks langsung
                helper.setText(notificationDTO.getBody(), true);
            }
            mailSender.send(mimeMessage);
            log.info("Email sent Out");

            // Simpan riwayat notifikasi ke tabel basis data
            Notification notificationToSave = Notification.builder()
                    .recipient(notificationDTO.getRecipinet())
                    .subject(notificationDTO.getSubject())
                    .body(notificationDTO.getBody())
                    .type(NotificationType.EMAIL)
                    .user(user)
                    .build();

            notificationRepo.save(notificationToSave);
        } catch (MessagingException e) {
            log.error(e.getMessage());
        }
    }
}
