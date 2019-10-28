package com.yzx.xiaoxiong581.customermanager.microservice.exception;

import com.yzx.xiaoxiong581.customermanager.microservice.exception.error.ResultErrorEnum;
import lombok.Data;

/**
 * @author xiaoxiong581
 */
@Data
public class BaseException extends RuntimeException {
    private String code;

    private String message;

    public BaseException() {
        super();
    }

    public BaseException(ResultErrorEnum resultErrorEnum) {
        this.code = resultErrorEnum.getCode();
        this.message = resultErrorEnum.getMessage();
    }

    public BaseException(ResultErrorEnum resultErrorEnum, Throwable e) {
        super(e);
        this.code = resultErrorEnum.getCode();
        this.message = resultErrorEnum.getMessage();
    }
}
