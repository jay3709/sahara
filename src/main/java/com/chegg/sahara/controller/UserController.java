package com.chegg.sahara.controller;

import com.chegg.sahara.model.ChatRequest;
import com.chegg.sahara.model.ChatResponse;
import com.chegg.sahara.service.AITherapistService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private AITherapistService aiTherapistService;

    @PostMapping("user/chat")
    public ChatResponse chatWithAI(@RequestBody ChatRequest chatRequest) throws JsonProcessingException {
        return aiTherapistService.chatWithAITherapist(chatRequest);
    }
}
