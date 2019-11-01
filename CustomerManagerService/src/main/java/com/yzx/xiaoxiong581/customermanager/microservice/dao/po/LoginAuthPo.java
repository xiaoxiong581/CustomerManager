package com.yzx.xiaoxiong581.customermanager.microservice.dao.po;

import lombok.Data;

import java.util.Date;

/**
 * @author xiaoxiong581
 */
@Data
public class LoginAuthPo {
    private String customerId;

    private String token;

    private Date updateTime = new Date();
}
