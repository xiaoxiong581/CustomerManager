package com.yzx.xiaoxiong581.customermanager.microservice.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应异常枚举类
 * <p>
 * 业务异常：10000~19999
 * 公共异常：100~999
 *
 * @author xiaoxiong581
 */
@AllArgsConstructor
public enum ResultErrorEnum {
    SERIALIZE_OBJECT_ERROR("100", "serialize object to string error"),

    DESERIALIZE_OBJECT_ERROR("101", "deserialize to object error"),

    SYSTEM_INTERNAL_EXCEPTION("102", "system internal exception"),

    USER_AUTH_FAIL("103", "user auth fail"),

    CUSTOMER_NOT_EXIST("10000", "customer not exist"),

    USERNAME_OR_PASSWORD_ERROR("10001", "userName or password error"),

    CUSTOMER_NAME_ALREADY_EXIST("10002", "customerName already exist"),

    CUSTOMER_STATUS_NOT_NORMAL("10003", "customer status isn't normal");

    @Getter
    private String code;

    @Getter
    private String message;
}
