package com.yzx.xiaoxiong581.customermanager.microservice.controller.customer;

import com.yzx.xiaoxiong581.customermanager.api.common.BaseResponse;
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


/**
 * @author xiaoxiong581
 */
@Slf4j
@RestController
public class CustomerAuthController implements ICustomerAuthService {
    @Autowired
    private CustomerDao customerDao;

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

        log.info("user {} login success.", request.getUserName());
        return new BaseResponse(customerPo);
    }
}
