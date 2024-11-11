package com.example.aiapistarter.controller;

import com.example.aiapistarter.request.PromptRequest;
import com.example.aiapistarter.response.AIResponse;
import com.example.aiapistarter.service.AIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    private final AIService aiService;

    @Autowired
    public OpenAIController(AIService aiService) {
        this.aiService = aiService;
    }

    @Operation(summary = "Generate text based on a prompt", description = "Sends a prompt to the OpenAI API and receives a generated response.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully generated text response"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Server error when calling AI service")
    })
    @PostMapping("/generate")
    public AIResponse generateText(
            @Valid @RequestBody
            @Parameter(description = "Request containing the prompt text to be sent to OpenAI") PromptRequest request) {
        String responseText = aiService.getAIResponse(request.getPrompt());
        return new AIResponse(responseText);
    }
}
