package com.tibame.timetotravel.common;

import com.tibame.timetotravel.dto.UserSessionDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;



@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserSessionDto user = (UserSessionDto) session.getAttribute("user");
        System.out.println(user);
        if (user == null) {
            // 未登入，進行相應的處理，例如導向登入頁面或返回錯誤訊息
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未登入");
            response.sendRedirect("/html/user_login.html");
            return false;
        }

        // 登入驗證通過，繼續執行後續處理
        response.sendRedirect("/html/user_login.html");
        return true;
    }
}
