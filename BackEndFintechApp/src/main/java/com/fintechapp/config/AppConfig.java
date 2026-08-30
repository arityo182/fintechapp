package com.fintechapp.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Kelas konfigurasi aplikasi untuk mendefinisikan bean pendukung (Template
 * Engine, ModelMapper).
 *
 * @author Ari
 * @since 1.0.0
 */
@Configuration
public class AppConfig {

    /**
     * Konfigurasi Thymeleaf Template Engine untuk memproses template HTML.
     * Mengatur lokasi template di folder classpath "templates/" dengan ekstensi
     * ".html" dan encoding UTF-8.
     *
     * @return instance {@link SpringTemplateEngine} yang telah dikonfigurasi
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        /* Membuat instance template engine utama */
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();

        /* Resolver untuk mencari file template di classpath */
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        /* File template berada di folder "templates/" pada classpath */
        templateResolver.setPrefix("templates/");
        /* File template berekstensi .html */
        templateResolver.setSuffix(".html");
        /* Menggunakan encoding UTF-8 agar mendukung karakter unicode */
        templateResolver.setCharacterEncoding("UTF-8");

        /* Menghubungkan resolver ke template engine */
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

    /**
     * Konfigurasi ModelMapper untuk memetakan objek antar layer (misalnya Entity ke
     * DTO).
     * Mengaktifkan pencocokan field dengan level akses private dan strategi
     * standar.
     *
     * @return instance {@link ModelMapper} yang telah dikonfigurasi
     */
    @Bean
    public ModelMapper modelMapperConfig() {
        ModelMapper modelMapper = new ModelMapper();

        /*
         * Mengaktifkan pencocokan field berdasarkan nama yang sama
         * Mengizinkan akses ke field bersifat private pada target object
         * sehingga mapping tetap berjalan meski field tidak punya getter/setter public
         */
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                /* Strategi STANDARD: nama field di source dan target harus sama persis */
                .setMatchingStrategy(MatchingStrategies.STANDARD);

        return modelMapper;
    }
}
