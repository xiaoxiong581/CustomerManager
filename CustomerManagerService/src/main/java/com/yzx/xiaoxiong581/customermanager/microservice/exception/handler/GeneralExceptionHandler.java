package com.yzx.xiaoxiong581.customermanager.microservice.exception.handler;

import com.yzx.xiaoxiong581.customermanager.api.common.BaseResponse;
import com.yzx.xiaoxiong581.customermanager.microservice.exception.BaseException;
import com.yzx.xiaoxiong581.customermanager.microservice.exception.error.ResultErrorEnum;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xiaoxiong581
 */
@ControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public BaseResponse handle(final BaseException baseException) {
        return new BaseResponse(baseException.getCode(), baseException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse handle(final Exception baseException) {
        return new BaseResponse(ResultErrorEnum.SYSTEM_INTERNAL_EXCEPTION.getCode(),
                ResultErrorEnum.SYSTEM_INTERNAL_EXCEPTION.getMessage());
    }
}
