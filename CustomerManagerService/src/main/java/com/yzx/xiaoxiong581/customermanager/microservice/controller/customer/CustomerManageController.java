package com.yzx.xiaoxiong581.customermanager.microservice.controller.customer;

import com.alibaba.fastjson.JSONObject;
import com.yzx.xiaoxiong581.customermanager.api.common.BaseResponse;
import com.yzx.xiaoxiong581.customermanager.api.customer.ICustomerManageService;
import com.yzx.xiaoxiong581.customermanager.api.customer.domain.AddCustomerRequest;
import com.yzx.xiaoxiong581.customermanager.api.customer.domain.QueryCustomerRequest;
import com.yzx.xiaoxiong581.customermanager.microservice.constant.CustomerStatus;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.CustomerDao;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.po.CustomerPo;
import com.yzx.xiaoxiong581.customermanager.microservice.exception.error.ResultErrorEnum;
import com.yzx.xiaoxiong581.customermanager.microservice.service.CustomerService;
import com.yzx.xiaoxiong581.customermanager.microservice.utils.ObjectUtils;
import com.yzx.xiaoxiong581.customermanager.microservice.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @author xiaoxiong581
 */
@Slf4j
@RestController
public class CustomerManageController implements ICustomerManageService {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerDao customerDao;

    @Override
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse add(@RequestBody AddCustomerRequest request) {
        if (customerService.isCustomerNameExist(request.getCustomerName())) {
            return ResponseUtils.newErrorRspByErrorEnum(ResultErrorEnum.CUSTOMER_NAME_ALREADY_EXIST);
        }

        String customerId = UUID.randomUUID().toString().replace("-", "");
        customerService.addCustomer(customerId, request);

        log.info("add customer success, customerId: {}, customerName: {}", customerId, request.getCustomerName());
        return new BaseResponse(customerDao.queryByCustomerId(customerId));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse query(@RequestBody QueryCustomerRequest request) {
        CustomerPo customerPo = ObjectUtils.deserializeToObject(request, CustomerPo.class);
        int offset = (request.getPageNo() - 1) * request.getPageSize();
        List<CustomerPo> customerPos = customerDao.query(customerPo, request.getStartTime(), request.getEndTime(),
                offset, request.getPageSize());
        int totalCount = customerDao.count(customerPo, request.getStartTime(), request.getEndTime());

        JSONObject data = new JSONObject();
        data.put("customers", customerPos);
        data.put("totalCount", totalCount);

        return new BaseResponse(data);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse queryById(@PathVariable("customerId") String customerId) {
        CustomerPo customerPo = customerDao.queryByCustomerId(customerId);
        if (null == customerPo) {
            return ResponseUtils.newErrorRspByErrorEnum(ResultErrorEnum.CUSTOMER_NOT_EXIST);
        }
        if (CustomerStatus.NORMAL != customerPo.getStatus()) {
            return ResponseUtils.newErrorRspByErrorEnum(ResultErrorEnum.CUSTOMER_STATUS_NOT_NORMAL);
        }

        return new BaseResponse(customerPo);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse lock(@PathVariable("customerId") String customerId) {
        CustomerPo customerPo = customerDao.queryByCustomerId(customerId);
        if (null == customerPo) {
            return ResponseUtils.newErrorRspByErrorEnum(ResultErrorEnum.CUSTOMER_NOT_EXIST);
        }
        if (CustomerStatus.NORMAL != customerPo.getStatus()) {
            return ResponseUtils.newErrorRspByErrorEnum(ResultErrorEnum.CUSTOMER_STATUS_NOT_NORMAL);
        }

        CustomerPo updateCustomerPo = new CustomerPo();
        updateCustomerPo.setStatus(CustomerStatus.LOCK);
        updateCustomerPo.setCustomerId(customerId);
        customerDao.update(updateCustomerPo);

        log.info("lock user {} success.", customerPo.getCustomerName());
        return new BaseResponse();
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse delete(@PathVariable("customerId") String customerId) {
        CustomerPo customerPo = customerDao.queryByCustomerId(customerId);
        if (null == customerPo) {
            return ResponseUtils.newErrorRspByErrorEnum(ResultErrorEnum.CUSTOMER_NOT_EXIST);
        }

        customerService.deleteCustomer(customerId);

        log.info("delete user {} success.", customerPo.getCustomerName());
        return new BaseResponse();
    }
}
