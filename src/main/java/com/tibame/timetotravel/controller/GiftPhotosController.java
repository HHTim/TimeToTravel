package com.tibame.timetotravel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/giftPhotosController")
public class GiftPhotosController implements Serializable {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/redis/add")
    public String addRedis() {
        redisTemplate.opsForValue().set("hi", "how r u");
        return "success!";
    }

    @GetMapping("/redis/get")
    public Object getRedis() {
        Object value = redisTemplate.opsForValue().get("hi");
        return value;
    }

}
