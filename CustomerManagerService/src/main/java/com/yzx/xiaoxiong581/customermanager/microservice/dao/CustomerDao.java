package com.yzx.xiaoxiong581.customermanager.microservice.dao;

import com.yzx.xiaoxiong581.customermanager.microservice.dao.po.CustomerPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author xiaoxiong581
 */
@Mapper
public interface CustomerDao {
    public int insert(CustomerPo customerPo);

    public int update(CustomerPo customerPo);

    public void delete(@Param("customerId") String customerId);

    public CustomerPo queryByCustomerId(@Param("customerId") String customerId);

    public CustomerPo queryByCustomerName(@Param("customerName") String customerName);

    public CustomerPo queryByEmail(@Param("email") String email);

    public List<CustomerPo> query(CustomerPo customerPo, @Param("startTime") Date startTime,
                                  @Param("endTime") Date endTime,
                                  @Param("offset") int offset,
                                  @Param("rows") int rows);

    public int count(CustomerPo customerPo, @Param("startTime") Date startTime,
                     @Param("endTime") Date endTime);
}
