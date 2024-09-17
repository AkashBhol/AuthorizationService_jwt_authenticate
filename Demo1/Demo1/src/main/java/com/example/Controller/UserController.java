package com.example.Controller;

import com.example.service.DemoService;
import com.example.dto.JwtRequest;
import com.example.interceptor.AuthInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final DemoService demoService;

    private final AuthInterceptor authInterceptor;

    @PostMapping("/user/login")
    public String login(@RequestBody JwtRequest request) {
        ResponseEntity<?> login = authInterceptor.login(request);
        return login.getBody().toString();
    }
}
