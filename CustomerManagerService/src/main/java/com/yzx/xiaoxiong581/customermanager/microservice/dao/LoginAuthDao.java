package com.yzx.xiaoxiong581.customermanager.microservice.dao;

import com.yzx.xiaoxiong581.customermanager.microservice.dao.po.LoginAuthPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * @author xiaoxiong581
 */
@Mapper
public interface LoginAuthDao {
    public int insert(LoginAuthPo loginAuthPo);

    public int updateTimeByAuth(LoginAuthPo loginAuthPo);

    public void deleteExpireLoginAuth(@Param("expireTime") Date expireTime);

    public void deleteLoginAuth(@Param("customerId") String customerId, @Param("token") String token);
}
