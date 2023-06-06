package com.tibame.timetotravel.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@Component
//@WebFilter(urlPatterns = {"/roomController/*", "/privateSceneController/*", "/html/user_login.html"})
public class CompanyFilter extends OncePerRequestFilter {


    /**
     * shouldNotFilter()
     *
     */

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
//        System.out.println(path);
        return !"/html/user_login.html".equals(path);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String httpMethod = request.getMethod();
        System.out.println(httpMethod);
        response.sendRedirect("/index.html");
//        // 是PUT、POST、DELETE方法才判斷有沒有登入
//        if (isModificationMethod(httpMethod)) {
//            // 判斷是DML方法後，才判斷有沒有登入
//            if (isCompanyLoggedIn(request, response)) {
//                // 回傳true => 有登入，就繼續往下
//                filterChain.doFilter(request, response);
//            } else {
//                // 沒有登入，就請他登入
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 設置響應狀態為401，表示未經授權的請求
//                response.getWriter().write("請先登入");
//                return;
//            }
//        } else {
//            filterChain.doFilter(request, response);
//        }

    }

    // 判斷廠商是否登入?
    private boolean isCompanyLoggedIn(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // (false) => 沒有HttpSession時，不要創建新的HttpSession
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("company_id") != null) {
            return true;
        } else {
            /**
             * 這裡不用手動設置Session，是因為Spring boot 只要sendRedirect就會設置
             * HttpSession session = request.getSession(false);
             *  if (session == null || session.getAttribute("company") == null) {
             * HttpSession newSession = request.getSession(true);
             * newSession.setAttribute("company", company);
             * response.sendRedirect("/login");
             * }
             */
            response.sendRedirect("/html/user_login.html");
            return false;
        }
    }

    // 判斷登入後，是否為DML方法
    private boolean isModificationMethod(String httpMethod) {
        return httpMethod.equals("POST") || httpMethod.equals("PUT") || httpMethod.equals("DELETE");
    }
}
