package com.coshop.core.common.exception;

import com.coshop.core.common.enums.ResponseCode;
import lombok.Getter;

/**
 * @author dixiey
 * @version 1.0
 * @description: TODO
 * @date 2023/11/26 15:51
 */
@Getter
public class AuroraRuntimeException extends RuntimeException {


    private final ResponseCode code;

    public AuroraRuntimeException() {
        super(String.format("%s", ResponseCode.INTERNAL_ERROR.getMessage()));
        this.code = ResponseCode.INTERNAL_ERROR;
    }

    public AuroraRuntimeException(Throwable e) {
        super(e);
        this.code = ResponseCode.INTERNAL_ERROR;
    }

    public AuroraRuntimeException(String msg) {
        this(ResponseCode.INTERNAL_ERROR, msg);
    }

    public AuroraRuntimeException(ResponseCode code) {
        super(String.format("%s", code.getMessage()));
        this.code = code;
    }

    public AuroraRuntimeException(ResponseCode code, String msg) {
        super(msg);
        this.code = code;
    }




}
