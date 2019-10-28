package com.yzx.xiaoxiong581.customermanager.api.customer.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;

/**
 * @author xiaoxiong581
 */
@Data
public class QueryCustomerRequest {
    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("status")
    private int status;

    @JsonProperty("startTime")
    private Date startTime;

    @JsonProperty("endTime")
    private Date endTime;

    @JsonProperty("pageNo")
    @Min(value = 1, message = "pageNo should >= 1")
    private int pageNo = 1;

    @JsonProperty("pageSize")
    @Min(value = 1, message = "pageSize should >= 1")
    @Max(value = 100, message = "pageSize should <= 100")
    private int pageSize = 10;
}
