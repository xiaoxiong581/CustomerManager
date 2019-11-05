package com.yzx.xiaoxiong581.customermanager.microservice.controller.customer;

import com.alibaba.fastjson.JSONObject;
import com.yzx.xiaoxiong581.customermanager.api.common.BaseResponse;
import com.yzx.xiaoxiong581.customermanager.api.customer.domain.LogoutRequest;
import com.yzx.xiaoxiong581.customermanager.api.customer.domain.RegisterRequest;
import com.yzx.xiaoxiong581.customermanager.microservice.constant.ResponseParams;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.LoginAuthDao;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.po.LoginAuthPo;
import com.yzx.xiaoxiong581.customermanager.microservice.exception.error.ResultErrorEnum;
import com.yzx.xiaoxiong581.customermanager.api.customer.ICustomerAuthService;
import com.yzx.xiaoxiong581.customermanager.api.customer.domain.LoginRequest;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.CustomerDao;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.po.CustomerPo;
import com.yzx.xiaoxiong581.customermanager.microservice.service.CustomerService;
import com.yzx.xiaoxiong581.customermanager.microservice.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


/**
 * @author xiaoxiong581
 */
@Slf4j
@RestController
public class CustomerAuthController implements ICustomerAuthService {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LoginAuthDao loginAuthDao;

    @Autowired
    private CustomerService customerService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse login(@RequestBody LoginRequest request) {
        CustomerPo customerPo = customerDao.queryByCustomerName(request.getUserName());
        if (null == customerPo || StringUtils.isEmpty(customerPo.getCustomerId())) {
            return ResponseUtils.newErrorRspByErrorEnum(ResultErrorEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        if (!customerService.isLoginSuccess(customerPo.getCustomerId(), request.getPassword())) {
            return ResponseUtils.newErrorRspByErrorEnum(ResultErrorEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        String token = customerService.addLoginAuth(customerPo.getCustomerId());
        JSONObject rspData = new JSONObject();
        rspData.put(ResponseParams.CUSTOMER_ID, customerPo.getCustomerId());
        rspData.put(ResponseParams.TOKEN, token);

        log.info("user {} login success.", request.getUserName());
        return new BaseResponse(rspData);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse logout(LogoutRequest request) {
        loginAuthDao.deleteLoginAuth(request.getCustomerId(), request.getToken());
        return new BaseResponse();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse register(RegisterRequest request) {
        if (customerService.isCustomerNameExist(request.getCustomerName())) {
            return ResponseUtils.newErrorRspByErrorEnum(ResultErrorEnum.CUSTOMER_NAME_ALREADY_EXIST);
        }

        if (customerService.isEmailExist(request.getEmail())) {
            return ResponseUtils.newErrorRspByErrorEnum(ResultErrorEnum.EMAIL_ALREADY_EXIST);
        }

        String customerId = UUID.randomUUID().toString().replace("-", "");
        customerService.addCustomer(customerId, request);

        log.info("register customer success, customerId: {}, customerName: {}", customerId, request.getCustomerName());
        return new BaseResponse(customerDao.queryByCustomerId(customerId));
    }
}
