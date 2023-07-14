package com.chegg.sahara.service;

import com.chegg.sahara.configuration.ApplicationConfiguration;
import com.chegg.sahara.model.ChatRequest;
import com.chegg.sahara.model.ChatResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static com.chegg.sahara.configuration.Constants.assistantRole;
import static com.chegg.sahara.configuration.Constants.userRole;

@Service
@Slf4j
public class AITherapistService {

    @Autowired
    private OpenAiService openAiService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("systemMessage")
    private ChatMessage systemChatMessage;

    @Autowired
    private ApplicationConfiguration applicationConfiguration;

    List<ChatMessage> chatMessages = new ArrayList<>();

    public ChatResponse chatWithAITherapist(ChatRequest chatRequest) {
        try {
            // add system message
            if (CollectionUtils.isEmpty(chatMessages)) {
                chatMessages.add(systemChatMessage);
            }

            // build chat message
            chatMessages.add(
                    new ChatMessage(
                            userRole,
                            chatRequest.getMessage(),
                            chatRequest.getName()));

            // build request
            ChatCompletionRequest completionRequest =
                    ChatCompletionRequest.builder()
                            .model(applicationConfiguration.getAiModel())
                            .messages(chatMessages)
                            .temperature(applicationConfiguration.getAiTemp())
                            .maxTokens(applicationConfiguration.getAiMaxTokens())
                            .topP(applicationConfiguration.getAiTopP())
                            .frequencyPenalty(applicationConfiguration.getAiFreqPenalty())
                            .presencePenalty(applicationConfiguration.getAiPresencePenalty())
                            .build();

            //call service method for chatting with AI
            List<ChatCompletionChoice> completionChoices =
                    openAiService.createChatCompletion(completionRequest).getChoices();

            //take content from the completion choice
            ChatCompletionChoice completionChoice = completionChoices.get(0);
            String content = completionChoice.getMessage().getContent();

            //add ai response in chat history
            chatMessages.add(new ChatMessage(assistantRole, content));

            //build chat response object with ai response
            ChatResponse chatResponse = objectMapper.readValue(content, ChatResponse.class);
            return chatResponse;
        }
        catch (Exception e){
            log.error("Some error occurred {}", e);
            return new ChatResponse("Some error occurred", null, null, null);
        }
    }

}
