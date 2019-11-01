package com.yzx.xiaoxiong581.customermanager.microservice.interceptor;

import com.yzx.xiaoxiong581.customermanager.api.common.BaseResponse;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.LoginAuthDao;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.po.LoginAuthPo;
import com.yzx.xiaoxiong581.customermanager.microservice.domain.RequestAuthContext;
import com.yzx.xiaoxiong581.customermanager.microservice.exception.error.ResultErrorEnum;
import com.yzx.xiaoxiong581.customermanager.microservice.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author xiaoxiong581
 */
@Slf4j
@Component
public class UserAuthInterceptor implements HandlerInterceptor {
    @Autowired
    private LoginAuthDao loginAuthDao;

    @Value("${customermanager.auth.excludeurls:}")
    private List<String> exculdeUrls;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String url = request.getRequestURI();
        if ((null != exculdeUrls && exculdeUrls.contains(url))) {
            return true;
        }

        String customerId = request.getHeader("auth_customerId");
        String token = request.getHeader("auth_token");

        if (StringUtils.isEmpty(customerId) || StringUtils.isEmpty(token)) {
            log.warn("{} auth failed", url);
            constructUnauthRsp(response);
            return false;
        }

        LoginAuthPo loginAuthPo = new LoginAuthPo();
        loginAuthPo.setCustomerId(customerId);
        loginAuthPo.setToken(token);
        int updateNum = loginAuthDao.updateTimeByAuth(loginAuthPo);
        if (updateNum < 1) {
            log.warn("{} auth failed, customerId: {}, token: {}", url, customerId, token);
            constructUnauthRsp(response);
            return false;
        }

        RequestAuthContext requestAuthContext = new RequestAuthContext();
        requestAuthContext.setCustomerId(customerId);
        RequestAuthContext.setRequestAuthContext(requestAuthContext);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        RequestAuthContext.setRequestAuthContext(null);
    }

    private void constructUnauthRsp(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter()
                .print(ObjectUtils.serializeObjectToStr(new BaseResponse(ResultErrorEnum.USER_AUTH_FAIL.getCode(),
                        ResultErrorEnum.USER_AUTH_FAIL.getMessage())));
    }
}
