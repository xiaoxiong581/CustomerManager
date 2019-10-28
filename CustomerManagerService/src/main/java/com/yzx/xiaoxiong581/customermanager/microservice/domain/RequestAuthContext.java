package com.yzx.xiaoxiong581.customermanager.microservice.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author xiaoxiong581
 */
@Getter
public class RequestAuthContext {
    @Setter
    private String customerId;

    @Setter
    private String customerName;

    @Setter
    private int status = -1;

    @Setter
    private Date createTime;

    @Setter
    private Date updateTime;

    private static final ThreadLocal<RequestAuthContext> AUTH_CONTEXT = new ThreadLocal<>();


    public static void setRequestAuthContext(RequestAuthContext requestAuthContext) {
        AUTH_CONTEXT.set(requestAuthContext);
    }

    public static RequestAuthContext getRequestAuthContext() {
        return AUTH_CONTEXT.get();
    }
}
