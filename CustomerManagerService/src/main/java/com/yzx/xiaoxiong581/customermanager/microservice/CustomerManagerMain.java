package com.yzx.xiaoxiong581.customermanager.microservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xiaoxiong581
 */
@SpringBootApplication
@MapperScan("com.yzx.xiaoxiong581.customermanager.microservice.dao")
public class CustomerManagerMain {
    public static void main(String[] args) {
        SpringApplication.run(CustomerManagerMain.class, args);
    }
}
