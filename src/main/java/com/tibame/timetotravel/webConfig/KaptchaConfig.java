package com.tibame.timetotravel.webConfig;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KaptchaConfig {
    @Bean
    public Producer KaptchaProducer() {
        Properties kaptchaProperties = new Properties();
        kaptchaProperties.put("kaptcha.border", "no");
        kaptchaProperties.put("kaptcha.textproducer.char.length", "4");
        kaptchaProperties.put("kaptcha.image.height", "45");
        kaptchaProperties.put("kaptcha.image.width", "190");
        kaptchaProperties.put("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");
        kaptchaProperties.put("kaptcha.textproducer.font.color", "black");
        kaptchaProperties.put("kaptcha.textproducer.font.size", "30");
        kaptchaProperties.put("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise");
        kaptchaProperties.put("kaptcha.textproducer.char.string", "acdefhkmnprtwxy2345678");

        Config config = new Config(kaptchaProperties);
        return config.getProducerImpl();
    }
}
