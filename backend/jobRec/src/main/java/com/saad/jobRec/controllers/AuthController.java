package com.saad.jobRec.controllers;
import com.saad.jobRec.dtos.AuthRequest;
import com.saad.jobRec.dtos.AuthResponse;
import com.saad.jobRec.dtos.MessageResponse;
import com.saad.jobRec.dtos.RegisterRequest;
import com.saad.jobRec.services.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest loginRequest) {
        System.out.println(">>> Received login: " + loginRequest);
        AuthResponse response = authService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody RegisterRequest request) {
        System.out.println(">>> Received registration: " + request);
        MessageResponse response = authService.register(request);
        if (response.getMessage().startsWith("Error")) {
            return ResponseEntity.badRequest().body(response);
        }
        return ResponseEntity.ok(response);
    }
}