package com.draftly.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Map;

@Service
public class AIService {
    private final WebClient webClient;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    public AIService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public String generateReply(String sender, String subject, String emailBody, String tone) {
        String prompt = buildPrompt(sender, subject, emailBody, tone);
        try {
            Map<String, Object> req = Map.of(
                    "model", "gpt-4o-mini",
                    "messages", new Object[] {
                            Map.of("role", "system", "content", "You are an assistant that writes concise, polite email replies."),
                            Map.of("role", "user", "content", prompt)
                    },
                    "temperature", 0.6
            );

            Map response = webClient.post()
                    .uri(apiUrl)
                    .header("Authorization", "Bearer " + apiKey)
                    .bodyValue(req)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            if (response == null || !response.containsKey("choices")) {
                return "No response from AI";
            }
            var choices = (java.util.List<Map<String, Object>>) response.get("choices");
            Map first = choices.get(0);
            Map message = (Map) first.get("message");
            return message.get("content").toString();
        } catch (WebClientResponseException e) {
            e.printStackTrace();
            return "AI API error: " + e.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
            return "AI service error: " + e.getMessage();
        }
    }

    private String buildPrompt(String sender, String subject, String emailBody, String tone) {
        return String.format(
                "You received an email from %s with subject '%s'.\n\nEmail:\n%s\n\nWrite a %s reply. Keep it short and polite. Include a suggested closing.",
                sender, subject, emailBody, tone == null ? "neutral" : tone
        );
    }
}
