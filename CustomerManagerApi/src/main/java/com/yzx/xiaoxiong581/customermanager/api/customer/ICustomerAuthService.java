package com.yzx.xiaoxiong581.customermanager.api.customer;

import com.yzx.xiaoxiong581.customermanager.api.common.BaseResponse;
import com.yzx.xiaoxiong581.customermanager.api.customer.domain.LoginRequest;
import com.yzx.xiaoxiong581.customermanager.api.customer.domain.LogoutRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiaoxiong581
 */
@RequestMapping("${version}/customer/auth")
public interface ICustomerAuthService {
    /**
     * 用户登录
     *
     * @param request 请求对象（用户名、用户密码）
     * @return 登录结果
     */
    @PostMapping(path = "login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse login(@RequestBody LoginRequest request);

    /**
     * 用户登出
     *
     * @param request 请求对象（用户Id、鉴权token）
     * @return 登出结果
     */
    @PostMapping(path = "logout", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse logout(@RequestBody LogoutRequest request);
}
