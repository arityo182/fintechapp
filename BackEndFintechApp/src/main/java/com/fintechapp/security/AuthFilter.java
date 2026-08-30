package com.fintechapp.security;

import com.fintechapp.exceptions.CustomAuthenticationEntryPoint;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter autentikasi untuk memproses dan memvalidasi JWT token pada setiap HTTP
 * request yang masuk.
 * Jika token valid, informasi autentikasi user akan disimpan ke dalam
 * SecurityContextHolder.
 *
 * @author Ari
 * @since 1.0.0
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Memproses setiap HTTP request yang masuk untuk memverifikasi token JWT,
     * memuat data user,
     * dan mengatur konteks autentikasi Spring Security.
     *
     * @param request     HttpServletRequest dari client
     * @param response    HttpServletResponse ke client
     * @param filterChain rantai filter lanjutan
     * @throws ServletException jika terjadi kesalahan servlet
     * @throws IOException      jika terjadi kesalahan I/O
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request);

        if (token != null) {
            String email;
            try {
                email = tokenService.getUserFromToken(token);
            } catch (Exception e) {
                log.error("Exception occured while extracting username from token");
                AuthenticationException authenticationException = new BadCredentialsException(e.getMessage());
                customAuthenticationEntryPoint.commence(request, response, authenticationException);
                return;
            }

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            if (StringUtils.hasText(email) && tokenService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        try {
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /**
     * Mengekstrak token JWT murni dari header Authorization pada HTTP request.
     * Mengabaikan prefix "Bearer " jika ada.
     *
     * @param request HttpServletRequest yang dikirim oleh client
     * @return Token JWT murni tanpa prefix "Bearer ", atau null jika tidak
     *         ditemukan/tidak valid
     */
    private String getTokenFromRequest(HttpServletRequest request) {

        String tokenWithBearer = request.getHeader("Authorization");
        if (tokenWithBearer != null && tokenWithBearer.startsWith("Bearer ")) {
            return tokenWithBearer.substring(7);
        }

        return null;
    }
}
