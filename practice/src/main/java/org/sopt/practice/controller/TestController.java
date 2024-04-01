package org.sopt.practice.controller;


import org.sopt.practice.controller.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        return "test API";
    }

    @GetMapping("/json")
    public ApiResponse testJson() {
        return ApiResponse.create("test API with JSON");
    }
}
