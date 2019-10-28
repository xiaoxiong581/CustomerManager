package com.yzx.xiaoxiong581.customermanager.microservice.service;

import com.yzx.xiaoxiong581.customermanager.api.customer.domain.AddCustomerRequest;
import com.yzx.xiaoxiong581.customermanager.microservice.constant.CustomerStatus;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.CustomerDao;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.LoginDao;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.po.CustomerPo;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.po.LoginPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiaoxiong581
 */
@Service
public class CustomerService {
    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private LoginDao loginDao;

    public boolean isLoginSuccess(String customerId, String password) {
        LoginPo loginPo = loginDao.queryByCustomerId(customerId);
        return null != loginPo && password.equals(loginPo.getPassword());
    }

    public String getCustomerIdByName(String customerName) {
        CustomerPo customerPo = customerDao.queryByCustomerName(customerName);
        return customerPo != null ? customerPo.getCustomerId() : null;
    }

    public boolean isCustomerNameExist(String customerName) {
        return null != getCustomerIdByName(customerName);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addCustomer(String customerId, AddCustomerRequest request) {
        CustomerPo customerPo = new CustomerPo();
        customerPo.setCustomerId(customerId);
        customerPo.setStatus(CustomerStatus.NORMAL);
        customerPo.setCustomerName(request.getCustomerName());
        customerDao.insert(customerPo);

        LoginPo loginPo = new LoginPo();
        loginPo.setCustomerId(customerId);
        loginPo.setPassword(customerId.substring(0, 10));
        loginDao.insert(loginPo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteCustomer(String customerId) {
        customerDao.delete(customerId);
        loginDao.delete(customerId);
    }
}
