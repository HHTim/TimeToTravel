package com.tibame.timetotravel.webConfig;

import com.tibame.timetotravel.common.AuthenticationInterceptor;
import com.tibame.timetotravel.common.CorsHandler;
import com.tibame.timetotravel.common.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private CorsHandler corsHandler;

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*===========================Admin==============================*/
        registry.addViewController("/admin").setViewName("forward:/html/admin.html");
        registry.addViewController("/admin_ann").setViewName("forward:/html/admin_ann.html");
        registry.addViewController("/admin_ann_publish").setViewName("forward:/html/admin_ann_publish.html");
        registry.addViewController("/admin_ann_detail").setViewName("forward:/html/admin_ann_detail.html");
        registry.addViewController("/admin_ann_edit").setViewName("forward:/html/admin_ann_edit.html");
        registry.addViewController("/admin_message_publish").setViewName("forward:/html/admin_message_publish.html");
        registry.addViewController("/admin_message_detail").setViewName("forward:/html/admin_message_detail.html");
        registry.addViewController("/admin_message_recv").setViewName("forward:/html/admin_message_recv.html");
        registry.addViewController("/admin_user_manager").setViewName("forward:/html/admin_user_manager.html");
        registry.addViewController("/admin_comp_manager").setViewName("forward:/html/admin_comp_manager.html");

        registry.addViewController("/user_message_manage").setViewName("forward:/html/user_message_manage.html");
        registry.addViewController("/scenes/search").setViewName("forward:/html/search.html");
        registry.addViewController("/rooms/search").setViewName("forward:/html/search_room.html");
        registry.addViewController("/rooms/booking").setViewName("forward:/html/booking_room.html");
//        registry.addViewController("/rooms/paid").setViewName("forward:/html/booking_paid.html");
//        registry.addViewController("/rooms/orders").setViewName("forward:/html/order_list.html");

        registry.addViewController("/company_message_manage").setViewName("forward:/html/company_message_manage.html");
        registry.addViewController("/admin_company_info").setViewName("forward:/html/admin_company_info.html");
        registry.addViewController("/admin_user_info").setViewName("forward:/html/admin_user_info.html");

        registry.addViewController("/user_register").setViewName("forward:/html/user_register.html");
        registry.addViewController("/user_login").setViewName("forward:/html/user_login.html");
        registry.addViewController("/gift_search").setViewName("forward:/html/gift_search.html");
        registry.addViewController("/gift_cart").setViewName("forward:/html/gift_cart.html");
        registry.addViewController("/gift_order").setViewName("forward:/html/gift_order_list.html");
        registry.addViewController("/chat").setViewName("forward:/html/chat.html");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/html/**")
                .addResourceLocations("classpath:/static/html/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(corsHandler).addPathPatterns("/**");
//        registry.addInterceptor(authenticationInterceptor).addPathPatterns("/roomController/**");
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns("/xxxController/**"); // 指定攔截的路徑模式
    }

}
