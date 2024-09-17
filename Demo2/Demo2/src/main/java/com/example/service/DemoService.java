package com.example.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DemoService {

    private final WebClient webClient;

    public DemoService(WebClient webClient) {
        this.webClient = webClient;
    }

    public ResponseEntity<?> getUser() {
        ResponseEntity<?> responseEntity = webClient.get()
                .uri("http://localhost:8089/api/users")
                .retrieve()
//                .toEntity(Object.class)
                .toEntity(String.class)
                .block();
        return responseEntity;
    }
 }
