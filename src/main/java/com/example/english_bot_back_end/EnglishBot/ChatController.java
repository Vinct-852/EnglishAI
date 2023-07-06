package com.example.english_bot_back_end.EnglishBot;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
public class ChatController {
    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @PostMapping("/chat/v2")
    public String chatV2(@RequestParam String prompt){
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);

        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "Please enter your sentence again...";
        }
        return response.getChoices().toString();
    }

    @PostMapping("/chat/v1")
    public ChatResponse chatV1(@RequestParam("prompt") String prompt) {

        ChatGPTRequest request = new ChatGPTRequest(model, prompt);

        ChatResponse response = restTemplate.postForObject(apiUrl, request, ChatResponse.class);
        return response;
    }

}
