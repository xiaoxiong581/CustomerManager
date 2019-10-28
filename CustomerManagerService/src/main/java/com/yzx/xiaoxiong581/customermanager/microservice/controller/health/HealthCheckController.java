package com.yzx.xiaoxiong581.customermanager.microservice.controller.health;

import com.yzx.xiaoxiong581.customermanager.api.health.IHealthCheckService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HealthCheckController implements IHealthCheckService {
    @Override
    @ResponseStatus(HttpStatus.OK)
    public String healthCheck() {
        return "success";
    }
}
