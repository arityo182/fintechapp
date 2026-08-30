package com.fintechapp.security;

import com.fintechapp.exceptions.CustomAccessDenialHandler;
import com.fintechapp.exceptions.CustomAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Konfigurasi Security Filter Chain untuk mendefinisikan aturan otorisasi HTTP
 * request,
 * manajemen session, proteksi CSRF, serta registrasi filter autentikasi kustom.
 *
 * @author Ari
 * @since 1.0.0
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityFilter {

    private final AuthFilter authFilter;
    private final CustomAccessDenialHandler customAccessDenialHandler;
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    /**
     * Mengkonfigurasi rantai filter keamanan HTTP (Security Filter Chain).
     * Menonaktifkan proteksi CSRF, menerapkan konfigurasi CORS default,
     * mengatur custom exception handler untuk akses ditolak (403) dan unauthorized
     * (401),
     * mengizinkan akses publik untuk endpoint "/api/auth/**", menetapkan session
     * management
     * tanpa state (STATELESS), serta mendaftarkan AuthFilter sebelum
     * UsernamePasswordAuthenticationFilter.
     *
     * @param httpSecurity objek pembangun konfigurasi keamanan HTTP
     * @return instansiasi {@link SecurityFilterChain} yang telah dikonfigurasi
     * @throws Exception jika terjadi kesalahan saat proses konfigurasi
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .exceptionHandling(ex -> ex.accessDeniedHandler(customAccessDenialHandler)
                        .authenticationEntryPoint(customAuthenticationEntryPoint))
                .authorizeHttpRequests(req -> req.requestMatchers("/api/auth/**").permitAll())
                .sessionManagement(mag -> mag.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    /**
     * Mendefinisikan bean enkripsi password dengan algoritma hashing BCrypt.
     * Digunakan untuk mengenkripsi password saat pendaftaran dan memverifikasinya
     * saat login.
     *
     * @return instansiasi {@link PasswordEncoder} berbasis BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Mendefinisikan bean {@link AuthenticationManager} untuk mengelola proses
     * autentikasi kredensial.
     *
     * @param authenticationConfiguration konfigurasi autentikasi Spring Security
     * @return instansiasi {@link AuthenticationManager}
     * @throws Exception jika gagal mengambil authentication manager dari
     *                   konfigurasi
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
