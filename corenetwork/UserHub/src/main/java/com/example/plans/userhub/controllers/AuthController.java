package com.example.plans.userhub.controllers;

import com.example.plans.sharedhub.models.response.StringResponse;
import com.example.plans.userhub.exceptions.AuthenticationException;
import com.example.plans.userhub.exceptions.UserNotFoundException;
import com.example.plans.userhub.models.request.SignInRequest;
import com.example.plans.userhub.models.request.SignUpRequest;
import com.example.plans.userhub.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/status")
    public String getStatus() {
        return "{\"status\":\"UP\"}";
    }

    @PostMapping("/signup")
    public StringResponse signup(@RequestBody @Valid SignUpRequest req) throws MessagingException, UserNotFoundException {
        log.info("Signup request received");
        authenticationService.register(req);
        return new StringResponse("", "Signup is successful.");
    }

    @PostMapping("/signin")
    public StringResponse signin(@RequestBody @Valid SignInRequest req) throws AuthenticationException {
        log.info("Signin request received");
        return authenticationService.authenticate(req);
    }
}
