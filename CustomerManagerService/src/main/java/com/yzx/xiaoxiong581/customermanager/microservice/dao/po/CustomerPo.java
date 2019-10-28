package com.yzx.xiaoxiong581.customermanager.microservice.dao.po;

import lombok.Data;

import java.util.Date;

/**
 * @author xiaoxiong581
 */
@Data
public class CustomerPo {
    private String customerId;

    private String customerName;

    private int status = -1;

    private Date createTime = new Date();

    private Date updateTime = new Date();
}
