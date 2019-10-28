package com.yzx.xiaoxiong581.customermanager.microservice.dao.po;

import lombok.Data;

import java.util.Date;

/**
 * @author xiaoxiong581
 */
@Data
public class LoginPo {
    private String customerId;

    private String password;

    private Date createTime = new Date();

    private Date updateTime = new Date();
}
