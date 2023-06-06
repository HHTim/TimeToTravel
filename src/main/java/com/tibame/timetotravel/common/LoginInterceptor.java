package com.tibame.timetotravel.common;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle被觸發了");
        Object companyId = request.getSession().getAttribute("company_id");
        Object userId = request.getSession().getAttribute("user_id");
        if(companyId == null || userId == null){
            System.out.println("啥都沒登入");
            response.sendRedirect("/html/user_login.html");
        }else if(companyId != null){
            System.out.println("廠商登入");
            response.sendRedirect("/html/company_info.html");
        }else {
            System.out.println("使用者登入");
            response.sendRedirect("/html/user_info.html");
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}
