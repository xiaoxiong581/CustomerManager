package com.yzx.xiaoxiong581.customermanager.api.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaoxiong581
 */
@Data
@NoArgsConstructor
public class BaseResponse {
    private String code = "0";

    private String message = "success";

    private Object data;

    public BaseResponse(Object data) {
        this.data = data;
    }

    public BaseResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
