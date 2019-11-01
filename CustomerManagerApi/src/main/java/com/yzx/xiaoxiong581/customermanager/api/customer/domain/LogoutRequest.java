package com.yzx.xiaoxiong581.customermanager.api.customer.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xiaoxiong581
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LogoutRequest {
    @JsonProperty("customerId")
    private String customerId;

    @JsonProperty("token")
    private String token;
}
