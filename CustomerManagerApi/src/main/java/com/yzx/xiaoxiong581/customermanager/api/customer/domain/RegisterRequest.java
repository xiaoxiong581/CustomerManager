package com.yzx.xiaoxiong581.customermanager.api.customer.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiaoxiong581
 */
@ToString(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegisterRequest extends Customer {
}
