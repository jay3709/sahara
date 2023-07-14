package com.chegg.sahara.controller;

import com.chegg.sahara.model.ChatRequest;
import com.chegg.sahara.model.ChatResponse;
import com.chegg.sahara.service.AITherapistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AITherapistService aiTherapistService;

    @PostMapping("user/chat")
    public ChatResponse chatWithAI(@RequestBody ChatRequest chatRequest) {
        return aiTherapistService.chatWithAITherapist(chatRequest);
    }
}
