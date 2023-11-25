package com.clients.commons.logs;

import com.clients.commons.api.ApiRequestLog;
import com.clients.commons.constants.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Enumeration;
import java.util.UUID;

@Component
public class LggingFilter implements Filter {
    @Autowired
    private LogPrinter logPrinter;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uuid = UUID.randomUUID().toString();
        httpServletRequest.setAttribute(Const.REQUEST_ID, uuid);
        ApiRequestLog apiRequestLog = ApiRequestLog.builder()
                .requestId(uuid)
                .inData(LocalDateTime.now())
                .path(httpServletRequest.getRequestURI())
                .method(httpServletRequest.getMethod())
                .headers(getHeaders(httpServletRequest))
                .build();
        chain.doFilter(request, response);
        logPrinter.write(apiRequestLog);
    }

    public static String getHeaders(HttpServletRequest httpServletRequest){
        Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
        StringBuilder headers = new StringBuilder();
        while (headerNames.hasMoreElements()){
            String key = headerNames.nextElement();
            headers.append("[");
            headers.append(key);
            headers.append(":");
            headers.append(httpServletRequest.getHeaders(key));
            headers.append("]");
        }
        return headers.toString();
    }

    public static String getQueryParams(HttpServletRequest httpServletRequest){
        Enumeration<String> queryPamatersNames = httpServletRequest.getParameterNames();
        StringBuilder headers = new StringBuilder();
        while (queryPamatersNames.hasMoreElements()){
            String key = queryPamatersNames.nextElement();
            headers.append("[");
            headers.append(key);
            headers.append(":");
            headers.append(httpServletRequest.getParameter(key));
            headers.append("]");
        }
        return headers.toString();
    }
}
