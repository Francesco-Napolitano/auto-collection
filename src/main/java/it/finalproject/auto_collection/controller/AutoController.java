package it.finalproject.auto_collection.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutoController {

    @GetMapping("")
    public String hello(HttpServletRequest request) {
        return "Hello World";
    }
}
