package com.fintechapp.notification.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fintechapp.enums.NotificationType;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) untuk membawa payload data pengiriman notifikasi/email.
 *
 * @author Ari
 * @since 1.0.0
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDTO {

    private Long id;
    private String subject;

    @NotBlank(message = "Recipient is required")
    private String recipinet;

    private String body;
    private NotificationType type;
    private LocalDateTime createdAt;

    // Data template email dan variabel pendukung
    private String templateName;
    private Map<String, Object> templateVariables;
}
