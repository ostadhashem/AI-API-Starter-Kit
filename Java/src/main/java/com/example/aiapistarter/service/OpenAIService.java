package com.example.aiapistarter.service;

import com.example.aiapistarter.config.OpenAIConfigProperties;
import com.example.aiapistarter.exception.AIServiceException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Service
public class OpenAIService implements AIService {

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final OpenAIConfigProperties configProperties;

    public OpenAIService(HttpClient httpClient, OpenAIConfigProperties configProperties) {
        this.httpClient = httpClient;
        this.configProperties = configProperties;
    }

    @Override
    public String getAIResponse(String prompt) {
        try {
            String apiKey = System.getenv("OPENAI_API_KEY");
            if (apiKey == null || apiKey.isEmpty()) {
                throw new AIServiceException("OPENAI_API_KEY environment variable is not set.");
            }

            URI uri = URI.create(configProperties.getApiUrl());
            Map<String, Object> requestData = new HashMap<>();
            requestData.put("model", configProperties.getModel());
            requestData.put("prompt", prompt);
            requestData.put("max_tokens", configProperties.getMaxTokens());

            String requestBody = objectMapper.writeValueAsString(requestData);

            HttpRequest request = HttpRequest.newBuilder(uri)
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .build();

            log.info("Sending request to OpenAI with prompt: {}", prompt);

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            log.info("Received response from OpenAI");

            return response.body();
        } catch (IOException | InterruptedException e) {
            log.error("Failed to get response from OpenAI API", e);
            throw new AIServiceException("Failed to get response from OpenAI API", e);
        }
    }
}
