package com.fintechapp.exceptions;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintechapp.res.Response;

/**
 * Entry point autentikasi kustom untuk menangani request yang tidak terautentikasi (HTTP 401 Unauthorized).
 *
 * @author Ari
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    /**
     * Menangani kegagalan autentikasi dengan mengirimkan respon JSON 401 Unauthorized ke client.
     *
     * @param request HTTP request yang masuk
     * @param response HTTP response yang dikembalikan
     * @param authException exception autentikasi yang dilemparkan
     * @throws IOException jika terjadi kegagalan I/O penulisan response
     * @throws ServletException jika terjadi kesalahan servlet
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        Response<?> errorResponse = Response.builder()
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .message(authException.getMessage())
                .build();

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
