package com.yzx.xiaoxiong581.customermanager.microservice.scheduler;

import com.yzx.xiaoxiong581.customermanager.microservice.dao.LoginAuthDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author xiaoxiong581
 */
@Slf4j
@Component
public class CleanExpireLoginAuthScheduler {
    @Autowired
    private LoginAuthDao loginAuthDao;

    @Value("${token.existtime:10}")
    private int existTime;

    @Scheduled(initialDelay = 30000, fixedDelay = 60000)
    public void execute() {
        try {
            Date expireTime = DateUtils.addMinutes(new Date(), -existTime);
            log.info("begin to clean expire login auth info, expireTime: {}", expireTime);
            loginAuthDao.deleteExpireLoginAuth(expireTime);
        } catch (Exception e) {
            log.error("clean expire login auth occur error", e);
        }
    }
}
