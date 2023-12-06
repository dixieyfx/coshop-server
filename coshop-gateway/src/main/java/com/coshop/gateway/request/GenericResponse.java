package com.coshop.gateway.request;


import com.coshop.gateway.enums.ResponseCode;
import lombok.Data;

/**
 * @author dixiey
 * @version 1.0
 * @description: TODO
 * @date 2023/11/26 15:55
 */
@Data
public class GenericResponse<T> {

    private int code;

    private T data;

    private String message;

    public GenericResponse() {};

    public GenericResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public GenericResponse(int code, T data, String message) {
        this(code, data);
        this.message = message;
    }

    public GenericResponse(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.data = null;
        this.message = responseCode.getMessage();
    }

    public GenericResponse(ResponseCode responseCode, T data) {
        this(responseCode);
        this.data = data;
    }

    public GenericResponse(ResponseCode responseCode, T data, String message) {
        this(responseCode, data);
        this.message = message;
    }


}
