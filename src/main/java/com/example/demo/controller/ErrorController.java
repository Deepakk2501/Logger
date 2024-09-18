package com.example.demo.controller;

import com.example.demo.request.ErrorRequest;
import com.example.demo.service.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class ErrorController {
    @Autowired
    private ErrorService errorService;

    @PostMapping("/error")
    public void logError(){
        errorService.sendErrorAlert();
    }
}
