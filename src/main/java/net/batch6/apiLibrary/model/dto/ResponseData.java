package net.batch6.apiLibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ResponseData<T> {
        private Integer status;
        private String message;
        private T data;
}
