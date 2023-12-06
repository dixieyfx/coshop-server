package com.coshop.gateway.enums;

import lombok.Getter;

import javax.ws.rs.core.Response;


/**
 * @author dixiey
 * @version 1.0
 * @description: TODO
 * @date 2023/11/26 15:39
 */
@Getter
public enum ResponseCode {
    SUCCESS(0, "Success"),

    INTERNAL_ERROR(1, "服务器内部错误"),

    USER_INPUT_ERROR(2, "用户输入错误"),

    AUTHENTICATION_NEEDED(3, "Token过期或无效"),

    FORBIDDEN(4, "禁止访问"),

    TOO_FREQUENT_VISIT(5, "访问太频繁，请休息一会儿");

    private final int code;

    private final String message;

    private final Response.Status status;

    ResponseCode(int code, String message, Response.Status status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    ResponseCode(int code, String message) {
        this(code, message, Response.Status.INTERNAL_SERVER_ERROR);
    }


}
