package com.yzx.xiaoxiong581.customermanager.microservice.dao;

import com.yzx.xiaoxiong581.customermanager.microservice.dao.po.LoginPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author xiaoxiong581
 */
@Mapper
public interface LoginDao {
    public int insert(LoginPo loginPo);

    public int updatePasswordByCustomerId(LoginPo loginPo);

    public void delete(@Param("customerId") String customerId);

    public LoginPo queryByCustomerId(@Param("customerId") String customerId);
}
