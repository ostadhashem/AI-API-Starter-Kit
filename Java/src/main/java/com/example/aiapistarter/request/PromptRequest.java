package com.example.aiapistarter.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromptRequest {

    @NotBlank(message = "Prompt must not be blank")
    private String prompt;
}
