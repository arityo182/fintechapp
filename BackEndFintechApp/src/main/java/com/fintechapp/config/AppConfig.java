package com.fintechapp.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/* Menandai class ini sebagai sumber konfigurasi bean di Spring */
@Configuration
public class AppConfig {

    /*
     * Bean 1: Thymeleaf Template Engine
     * Mengkonfigurasi Thymeleaf agar bisa merender file HTML
     * sebagai view/template di aplikasi (misal halaman login, dll)
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

    /*
     * Bean 2: ModelMapper
     * Library untuk mapping/conversion antar object secara otomatis
     * Misal: Entity -> DTO atau DTO -> Entity tanpa kode manual
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
