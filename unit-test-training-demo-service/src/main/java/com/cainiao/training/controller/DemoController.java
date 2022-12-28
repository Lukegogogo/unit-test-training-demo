package com.cainiao.training.controller;

import com.cainiao.training.service.DemoService;
import com.cainiao.training.util.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;

@Controller
public class DemoController {

    @Autowired
    DemoService demoService;

    public String execute(String request) {

        if (!RequestValidator.valid(request)) {
            return "";
        }

        try {
            return demoService.getResult(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(String request) {

        String key = convertToKey(request);
        demoService.deleteByKey(key);
    }

    private String convertToKey(String request) {
        return request.trim();
    }
}
