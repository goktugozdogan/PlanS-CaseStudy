package com.example.plans.userhub.services;

import com.example.plans.sharedhub.models.response.StringResponse;
import com.example.plans.userhub.exceptions.AuthenticationException;
import com.example.plans.userhub.exceptions.UserNotFoundException;
import com.example.plans.userhub.models.request.SignInRequest;
import com.example.plans.userhub.models.request.SignUpRequest;
import com.example.plans.userhub.models.User;
import com.example.plans.userhub.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.MessagingException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public void register(@NotNull SignUpRequest req) throws MessagingException, UserNotFoundException {
        if (userRepository.existsById(req.getEmail())) {
            throw new UserNotFoundException("User with email: " + req.getEmail() + " already exists.");
        }

        User user = User.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .firstName(req.getFirstname())
                .lastName(req.getLastname())
                .email(req.getEmail())
                .phone(req.getPhone())
                .enabled(true)
                .accountLocked(true)
                .roles(req.getRoles())
                .devices(req.getDevices())
                .build();
        userRepository.save(user);
    }

    public StringResponse authenticate(@NotNull SignInRequest req) throws MessagingException, AuthenticationException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            req.getEmail(),
                            req.getPassword()
                    )
            );
        } catch (Exception e) {
            log.error("Authentication failed {}", e.getMessage());
            throw new AuthenticationException();
        }
        return new StringResponse("AUTH SUCCESS");
    }
}
