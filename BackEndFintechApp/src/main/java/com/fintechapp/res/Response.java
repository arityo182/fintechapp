package com.fintechapp.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * Format pembungkus standar (standard response wrapper) untuk semua respons API aplikasi.
 *
 * @param <T> tipe data payload utama yang dikembalikan dalam properti data
 * @author Ari
 * @since 1.0.0
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {

    private int statusCode;
    private String message;
    private T data;
    private Map<String, Serializable> meta;
}
