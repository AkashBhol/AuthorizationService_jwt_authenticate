package com.example.Intersepter;

import com.example.dto.JwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class AuthInterceptor {
    private WebClient webClient;

    public AuthInterceptor(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8089").build();
    }

    public ResponseEntity<?> login(JwtRequest request) {
        ResponseEntity<?> responseEntity = webClient.post()
                .uri("/api/authenticate") // Use the relative URI
                .bodyValue(request) // Set the request body
                .retrieve()
                .toEntity(String.class).block();
        return responseEntity;
    }

    public Boolean validateToken(String token) {
        ResponseEntity<Boolean> responseEntity = webClient.get()
                .uri("/api/token/validate?token=" + token)
                .retrieve()
//                .toEntity(Object.class)
                .toEntity(Boolean.class)
                .doOnError(error -> {
                    System.err.println("Request failed: " + error.getMessage());
                })
                .block();
        return responseEntity.getBody();
    }
}
