package com.example.Controller;

import com.example.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class Demo1Controller {

    @Autowired
    private WebClient webClient;

    @Autowired
    private TokenDTO tokenDTO;

    @GetMapping("/demo1/get")
    public String getAll(){
        ResponseEntity<?> data = getData();
        return "welcome to Demo1Controller";
    }

    public ResponseEntity<?> getData(){
        ResponseEntity<String> block = webClient.get().uri("http://localhost:8088/demo2/get")
                .header("Authorization", tokenDTO.getAccessToken())
                .retrieve()
                .toEntity(String.class)
                .block();
        return block;
    }
}
