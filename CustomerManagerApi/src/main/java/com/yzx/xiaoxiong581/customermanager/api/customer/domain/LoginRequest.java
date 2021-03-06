package com.yzx.xiaoxiong581.customermanager.api.customer.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author xiaoxiong581
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginRequest {
    @JsonProperty("userName")
    private String userName;

    @JsonProperty("password")
    private String password;
}
