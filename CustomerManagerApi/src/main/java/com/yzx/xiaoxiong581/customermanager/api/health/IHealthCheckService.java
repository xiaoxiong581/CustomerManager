package com.yzx.xiaoxiong581.customermanager.api.health;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiaoxiong581
 */
@RequestMapping("{version}")
public interface IHealthCheckService {
    /**
     * 健康检查方法
     *
     * @return 检查结果
     */
    @GetMapping(path = "healthcheck", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String healthCheck();
}
