package com.yzx.xiaoxiong581.customermanager.microservice.utils;

import com.yzx.xiaoxiong581.customermanager.api.common.BaseResponse;
import com.yzx.xiaoxiong581.customermanager.microservice.exception.error.ResultErrorEnum;

/**
 * @author xiaoxiong581
 */
public class ResponseUtils {
    public static BaseResponse newErrorRspByErrorEnum(ResultErrorEnum resultErrorEnum) {
        return new BaseResponse(resultErrorEnum.getCode(), resultErrorEnum.getMessage());
    }
}
