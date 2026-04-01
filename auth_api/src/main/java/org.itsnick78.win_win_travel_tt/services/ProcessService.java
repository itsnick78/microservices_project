package org.itsnick78.win_win_travel_tt.services;

import org.itsnick78.win_win_travel_tt.models.ProcessLog;
import org.itsnick78.win_win_travel_tt.models.User;
import org.itsnick78.win_win_travel_tt.repositories.ProcessLogRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class ProcessService {
    private final ProcessLogRepository logRepository;
    private final RestTemplate restTemplate;

    public ProcessService(ProcessLogRepository logRepository, RestTemplate restTemplate) {
        this.logRepository = logRepository;
        this.restTemplate = restTemplate;
    }

    @Value("${internal.token}")
    private String internalToken;

    @Value("${service-b.url}")
    private String serviceBUrl;

    public String processAndLog(String text, User user) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Internal-Token", internalToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("text", text);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        try {
            ParameterizedTypeReference<Map<String, String>> responseType =
                    new ParameterizedTypeReference<>() {};

            ResponseEntity<Map<String, String>> response = restTemplate.exchange(
                    serviceBUrl + "/api/transform",
                    HttpMethod.POST,
                    entity,
                    responseType
            );

            String transformed = Objects.requireNonNull(response.getBody()).get("result");

            ProcessLog log = new ProcessLog(user, text, transformed);
            logRepository.save(log);

            return transformed;
        } catch (Exception e) {
            throw new RuntimeException("Failed to communicate with Service B: " + e.getMessage());
        }
    }
}
