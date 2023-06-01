package com.tibame.timetotravel.controller;

import com.google.gson.Gson;
import jakarta.validation.ConstraintViolation;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Set;

public class BaseController {
    private Gson gson = new Gson();

    protected <T> String parseErrorMessage(Set<ConstraintViolation<T>> constraintViolations) {
        Map<String, Map<String, String>> map = new HashedMap();
        Map<String, String> message = new HashedMap();
        map.put("error_message", message);

        constraintViolations.forEach(row -> {
            message.put(row.getPropertyPath().toString(), row.getMessage());
        });

        return gson.toJson(map);
    }

    protected String parseErrorMessage(String message) {
        Map<String, String> map = new HashedMap();
        map.put("error_message", message);
        
        return gson.toJson(map);
    }

    protected <T> ResponseEntity badRequest(Set<ConstraintViolation<T>> constraintViolations) {
        return ResponseEntity.badRequest().body(parseErrorMessage(constraintViolations));
    }

    protected ResponseEntity badRequest(String message) {
        return ResponseEntity.badRequest().body(parseErrorMessage(message));
    }
}
