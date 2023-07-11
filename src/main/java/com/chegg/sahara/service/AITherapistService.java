package com.chegg.sahara.service;

import com.chegg.sahara.model.ChatRequest;
import com.chegg.sahara.model.ChatResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class AITherapistService {

    @Autowired
    private OpenAiService openAiService;

    @Autowired
    private ObjectMapper objectMapper;

    List<ChatMessage> chatMessages = new ArrayList<>();

    public ChatResponse chatWithAITherapist(ChatRequest chatRequest) throws JsonProcessingException {

        if(CollectionUtils.isEmpty(chatMessages)){
            ChatResponse chatResponse = new ChatResponse();
            chatResponse.setPrompt("How are you feeling?");
            chatMessages.add(
                    new ChatMessage(
                            "system",
                            "How are you feeling?"));
            return chatResponse;
        }

        //build chat message
        chatMessages.add(
                new ChatMessage(
                        "user",
                        "User's message:"
                                + chatRequest.getMessage()
                                + "As a therapist addressing the user, put all response in a json object with Json Key and Json Value for user"
                                + chatRequest.getName()
                                + ", please keep these points:"
                                + "If user asks out of context questions, reply with Json Key: prompt, Json Value: I can not help with this right now."
                                + "Context: The user is a student seeking help."
                                + "Json Key: tips, Json Value: Provide tips for help."
                                + "Json Key: sentiment, Json Value: Sentiment analysis for whole conversation history in one word among (Happy, Neutral, Sad, Depressed, Low)."
                                + "Json Key: questions, Json Value: Ask for relevant questions.",
                        chatRequest.getName()));

        //build request
        ChatCompletionRequest completionRequest =
                ChatCompletionRequest.builder()
                        .model("gpt-3.5-turbo-16k")
                        .messages(chatMessages)
                        .temperature(0.9d)
                        .maxTokens(500)
                        .build();

        //call service method for chatting with AI
        List<ChatCompletionChoice> completionChoices =
                openAiService.createChatCompletion(completionRequest).getChoices();

        //take content from the completion choice
        ChatCompletionChoice completionChoice = completionChoices.get(0);
        String content = completionChoice.getMessage().getContent();

        //add ai response in chat history
        chatMessages.add(new ChatMessage("assistant", content));

        //build chat response object with ai response
        ChatResponse chatResponse = objectMapper.readValue(content, ChatResponse.class);
        return chatResponse;
    }
}
