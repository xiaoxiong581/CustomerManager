package com.yzx.xiaoxiong581.customermanager.microservice.dao;

import com.yzx.xiaoxiong581.customermanager.microservice.dao.po.LoginPo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginDaoTest {
    @Autowired
    private LoginDao loginDao;

    private final static String CUSTOMER_ID = UUID.randomUUID().toString();

    @Test
    public void testInsert() {
        LoginPo loginPo = new LoginPo();
        loginPo.setCustomerId(CUSTOMER_ID);
        loginPo.setPassword("fsaw342344");
        loginDao.insert(loginPo);
        log.info("success to insert login info");
    }

    @Test
    public void testUpdatePasswordByCustomerId() {
        LoginPo loginPo = new LoginPo();
        loginPo.setCustomerId(CUSTOMER_ID);
        loginPo.setPassword("fsaw342344555");
        loginDao.updatePasswordByCustomerId(loginPo);
        log.info("success to update password");
    }

    @Test
    public void testDelete() {
        loginDao.delete(CUSTOMER_ID);
        log.info("success to delete login info");
    }

    @Test
    public void testQueryByCustomerId() {
        log.info("query login: {}", loginDao.queryByCustomerId(CUSTOMER_ID));
    }
}