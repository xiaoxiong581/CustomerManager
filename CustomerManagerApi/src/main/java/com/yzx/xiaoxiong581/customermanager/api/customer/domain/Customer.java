package com.yzx.xiaoxiong581.customermanager.api.customer.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author xiaoxiong581
 */
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer {
    @JsonProperty("customerName")
    private String customerName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
