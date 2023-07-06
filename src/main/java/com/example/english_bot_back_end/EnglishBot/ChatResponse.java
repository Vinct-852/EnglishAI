package com.example.english_bot_back_end.EnglishBot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatResponse {
    private List<Choice> choices;

    @Data
    public static class Choice{
        private int index;
        private Message message;
    }
}
