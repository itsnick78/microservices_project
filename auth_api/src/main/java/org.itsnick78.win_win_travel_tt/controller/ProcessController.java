package org.itsnick78.win_win_travel_tt.controller;

import org.itsnick78.win_win_travel_tt.models.User;
import org.itsnick78.win_win_travel_tt.services.ProcessService;
import org.itsnick78.win_win_travel_tt.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ProcessController {
    private final ProcessService processService;
    private final UserService userService;

    public ProcessController(ProcessService processService, UserService userService) {
        this.processService = processService;
        this.userService = userService;
    }

    @PostMapping("/process")
    public ResponseEntity<Map<String, String>> process(@RequestBody Map<String, String> body, Principal principal) {
        User user = userService.getByEmail(principal.getName());

        String result = processService.processAndLog(body.get("text"), user);

        Map<String, String> response = new HashMap<>();
        response.put("result", result);

        return ResponseEntity.ok(response);
    }
}
