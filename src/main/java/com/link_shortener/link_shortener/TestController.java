package com.link_shortener.link_shortener;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TestController {

    @GetMapping("/")
    public String index() {
        return "Hello World";
    }
}
