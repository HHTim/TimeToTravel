package com.tibame.timetotravel.webConfig;

import com.tibame.timetotravel.common.SearchRoom;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class BeanConfig {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public SearchRoom searchRoom() {
        return new SearchRoom();
    }
}
