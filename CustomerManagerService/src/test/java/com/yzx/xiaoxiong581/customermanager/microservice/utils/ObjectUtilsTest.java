package com.yzx.xiaoxiong581.customermanager.microservice.utils;

import com.yzx.xiaoxiong581.customermanager.api.common.BaseResponse;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author xiaoxiong581
 */
@Ignore
public class ObjectUtilsTest {

    @Test
    public void serializeObjectToStr() {
        BaseResponse baseResponse = new BaseResponse();
        System.out.println(ObjectUtils.serializeObjectToStr(baseResponse));
    }

    @Test
    public void deserializeToObject() {
        String str = "{\"code\":\"0\",\"message\":\"success\",\"data\":null}";
        System.out.println(ObjectUtils.deserializeToObject(str, BaseResponse.class));
    }
}