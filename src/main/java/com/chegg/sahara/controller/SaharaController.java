package com.chegg.sahara.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaharaController {

        @GetMapping("/getLinks")
        public String getLinks(){
            return "welcome";
        }
}

