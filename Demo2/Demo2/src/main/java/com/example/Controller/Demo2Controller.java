package com.example.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo2Controller {

    @GetMapping("/demo2/get")
    public  String getAll(){
        return "welcome to demo254 page";
    }
}
