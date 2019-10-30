package com.yzx.xiaoxiong581.customermanager.microservice.filter;

import com.yzx.xiaoxiong581.customermanager.api.common.BaseResponse;
import com.yzx.xiaoxiong581.customermanager.microservice.constant.CustomerStatus;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.CustomerDao;
import com.yzx.xiaoxiong581.customermanager.microservice.dao.po.CustomerPo;
import com.yzx.xiaoxiong581.customermanager.microservice.domain.RequestAuthContext;
import com.yzx.xiaoxiong581.customermanager.microservice.exception.error.ResultErrorEnum;
import com.yzx.xiaoxiong581.customermanager.microservice.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author xiaoxiong581
 */
@Slf4j
@Component
@WebFilter(urlPatterns = "/${server.context-path}/${version}/*", filterName = "userAuthFilter")
public class UserAuthFilter implements Filter {
    @Autowired
    private CustomerDao customerDao;

    @Value("#{'${customermanager.auth.excludeurls}'.split(',')}")
    private List<String> exculdeUrls;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
        return;

       /* HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        if (null != exculdeUrls && exculdeUrls.contains(httpServletRequest.getRequestURI())) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        HttpSession currentUserSession = httpServletRequest.getSession();
        String customerId = (String) currentUserSession.getAttribute("customerId");

        if (StringUtils.isEmpty(customerId)) {
            constructForbiddenRsp(httpServletResponse);
            return;
        }

        CustomerPo customerPo = customerDao.queryByCustomerId(customerId);
        if (null == customerPo || CustomerStatus.NORMAL != customerPo.getStatus()) {
            constructForbiddenRsp(httpServletResponse);
            return;
        }

        RequestAuthContext requestAuthContext = ObjectUtils.deserializeToObject(customerPo, RequestAuthContext.class);
        RequestAuthContext.setRequestAuthContext(requestAuthContext);

        filterChain.doFilter(servletRequest, servletResponse);

        RequestAuthContext.setRequestAuthContext(null);*/
    }

    private void constructForbiddenRsp(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.getWriter()
                .print(ObjectUtils.serializeObjectToStr(new BaseResponse(ResultErrorEnum.USER_AUTH_FAIL.getCode(),
                        ResultErrorEnum.USER_AUTH_FAIL.getMessage())));
    }
}
