package com.fintechapp.exceptions;

import com.fintechapp.res.Response;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Handler kustom saat pengguna terautentikasi mencoba mengakses resource tanpa otorisasi yang cukup (HTTP 403 Forbidden).
 *
 * @author Ari
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class CustomAccessDenialHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    /**
     * Menangani kegagalan otorisasi dengan mengembalikan respons JSON 403 Forbidden standar.
     *
     * @param request HTTP request dari client
     * @param response HTTP response ke client
     * @param accessDeniedException exception access denied yang terjadi
     * @throws IOException jika terjadi kesalahan penulisan response stream
     * @throws ServletException jika terjadi kesalahan level servlet
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        Response<?> errorResponse = Response.builder()
                .statusCode(HttpStatus.FORBIDDEN.value())
                .message(accessDeniedException.getMessage())
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
