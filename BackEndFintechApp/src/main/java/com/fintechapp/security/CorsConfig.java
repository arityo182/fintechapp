package com.fintechapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Konfigurasi Cross-Origin Resource Sharing (CORS) untuk mengatur izin akses
 * API dari client lintas domain.
 *
 * @author Ari
 * @since 1.0.0
 */
@Configuration
public class CorsConfig {

    /**
     * Mendefinisikan bean filter CORS untuk mengizinkan request lintas domain
     * (Cross-Origin).
     * Mengatur origin yang diizinkan, header, method HTTP, serta durasi cache
     * preflight request.
     *
     * @return instansiasi {@link CorsFilter} yang telah dikonfigurasi
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.addAllowedOrigin("*"); // in production we will only allow origins from out trusted frontend
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        config.setMaxAge(3600L);

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
