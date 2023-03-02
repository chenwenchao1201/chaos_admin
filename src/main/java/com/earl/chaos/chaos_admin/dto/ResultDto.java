package com.earl.chaos.chaos_admin.dto;

import lombok.Data;

@Data
public class ResultDto {
    private String code;
    private Object result;
    private String message;

    public ResultDto(String code, Object result, String message) {
        this.code = code;
        this.result = result;
        this.message = message;
    }

    public static ResultDto success(String message, Object result) {
        return new ResultDto("200", result, message);
    }

    public static ResultDto fail(String message) {
        return new ResultDto("500", null, message);
    }


    public static ResultDto successWithoutResult(String message) {
        return new ResultDto("200", null, message);
    }

}
