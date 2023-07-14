package com.chegg.sahara.configuration;

import com.chegg.sahara.Sentiment;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

import static com.chegg.sahara.configuration.Constants.*;

@Configuration
@Getter
public class ApplicationConfiguration {

    @Value("${api.token:test-key}")
    private String apiToken;

    @Value("${ai.model}")
    private String aiModel;

    @Value("${ai.temp}")
    private Double aiTemp;

    @Value("${ai.max.tokens}")
    private Integer aiMaxTokens;

    @Value("${ai.top.p}")
    private Double aiTopP;

    @Value("${ai.freq.penalty}")
    private Double aiFreqPenalty;

    @Value("${ai.presence.penalty}")
    private Double aiPresencePenalty;
    @Bean
    public OpenAiService openAiService(){
        return new OpenAiService(apiToken, Duration.ofSeconds(60L));
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    @Qualifier("systemMessage")
    public ChatMessage systemMessage(){
        return new ChatMessage(systemRole, buildSystemMessage());
    }

    private String buildSystemMessage(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context);
        stringBuilder.append(usersFeedbackOnConversation);
        stringBuilder.append(keyRoleOfAssistant);
        stringBuilder.append(taskOfAssistant);
        stringBuilder.append(outOfContextMessage);
        stringBuilder.append(tipsMessage);
        stringBuilder.append(sentimentMessage + "("+String.join(", ", Sentiment.getSentiments())+")");
        stringBuilder.append(questionsMessage);
        stringBuilder.append(exampleMessageForOutOfContext);
        stringBuilder.append(exampleMessageForContext);
        return stringBuilder.toString();
    }
}
