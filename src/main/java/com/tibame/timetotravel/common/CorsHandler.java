package com.tibame.timetotravel.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class CorsHandler implements HandlerInterceptor {
    /**
     * 允許跨域請求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允許來自所有網域的請求
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE"); // 允許的 HTTP 方法
        response.setHeader("Access-Control-Allow-Headers", "Content-Type"); // 允許的請求頭
        response.setHeader("Access-Control-Allow-Credentials", "true"); // 是否允許帶有憑證的請求
        return true;
    }
}
