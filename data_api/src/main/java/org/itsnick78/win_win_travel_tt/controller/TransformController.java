package org.itsnick78.win_win_travel_tt.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransformController {
    @Value("${internal.token}")
    private String internalToken;

    @PostMapping("/transform")
    public ResponseEntity<?> transform(@RequestBody Map<String, String> body, @RequestHeader("X-Internal-Token") String token) {
        if (!token.equals(internalToken)) {
            return ResponseEntity.status(403).body("Access Denied");
        }

        String inputText = body.get("text");
        String processed = new StringBuilder(inputText).reverse().toString();
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("result", processed));
    }
}
