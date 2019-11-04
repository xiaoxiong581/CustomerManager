package com.yzx.xiaoxiong581.customermanager.microservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xiaoxiong581
 */
@EnableScheduling
@MapperScan("com.yzx.xiaoxiong581.customermanager.microservice.dao")
@ImportResource("classpath:spring/*.xml")
@SpringBootApplication
public class CustomerManagerMain {
    public static void main(String[] args) {
        SpringApplication.run(CustomerManagerMain.class, args);
    }
}
