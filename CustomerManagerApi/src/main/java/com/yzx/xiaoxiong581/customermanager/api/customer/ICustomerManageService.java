package com.yzx.xiaoxiong581.customermanager.api.customer;

import com.yzx.xiaoxiong581.customermanager.api.common.BaseResponse;
import com.yzx.xiaoxiong581.customermanager.api.customer.domain.AddCustomerRequest;
import com.yzx.xiaoxiong581.customermanager.api.customer.domain.LoginRequest;
import com.yzx.xiaoxiong581.customermanager.api.customer.domain.QueryCustomerRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaoxiong581
 */
@RequestMapping("${version}/customer/manage")
public interface ICustomerManageService {
    /**
     * 新增用户
     *
     * @param request 请求对象（用户名）
     * @return 添加结果
     */
    @PostMapping(path = "add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse add(@RequestBody AddCustomerRequest request);

    /**
     * 查询用户列表
     *
     * @param request 请求对象（用户名）
     * @return 用户列表
     */
    @PostMapping(path = "query", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse query(@RequestBody QueryCustomerRequest request);

    /**
     * 查询用户详情
     *
     * @param customerId 用户id
     * @return 用户详情
     */
    @GetMapping(path = "query/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse queryById(@PathVariable("customerId") String customerId);

    /**
     * 锁定用户
     *
     * @param customerId 用户id
     * @return 锁定结果
     */
    @PutMapping(path = "lock/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse lock(@PathVariable("customerId") String customerId);

    /**
     * 删除用户
     *
     * @param customerId 用户id
     * @return 删除结果
     */
    @DeleteMapping(path = "delete/{customerId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseResponse delete(@PathVariable("customerId") String customerId);
}
