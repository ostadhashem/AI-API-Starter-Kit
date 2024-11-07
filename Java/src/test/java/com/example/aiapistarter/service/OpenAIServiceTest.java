package com.example.aiapistarter.service;

import com.example.aiapistarter.config.OpenAIConfigProperties;
import com.example.aiapistarter.exception.AIServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.net.http.HttpClient;

import static org.junit.jupiter.api.Assertions.assertThrows;

class OpenAIServiceTest {

    private OpenAIService openAIService;

    @BeforeEach
    void setUp() {
        OpenAIConfigProperties configProperties = new OpenAIConfigProperties();
        configProperties.setApiUrl("https://api.openai.com/v1/completions");
        configProperties.setModel("text-davinci-003");
        configProperties.setMaxTokens(50);

        HttpClient httpClient = HttpClient.newHttpClient();
        openAIService = new OpenAIService(httpClient, configProperties);
    }

    @Test
    void testGetAIResponseWithEmptyPromptShouldThrowException() {
        assertThrows(AIServiceException.class, () -> openAIService.getAIResponse(""));
    }
}
