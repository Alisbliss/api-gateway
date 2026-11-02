package com.innowise.apigateway.service;

import com.innowise.apigateway.client.AuthClient;
import com.innowise.apigateway.client.UserClient;
import com.innowise.apigateway.dto.RegisterInternalRequest;
import com.innowise.apigateway.dto.RegistrationRequest;
import com.innowise.apigateway.dto.UserCreateRequest;
import com.innowise.apigateway.dto.UserResponse;
import com.innowise.apigateway.security.InternalTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationOrchestrator {

    private final UserClient userClient;
    private final AuthClient authClient;
    private final InternalTokenProvider tokenProvider;

    public UserResponse register(RegistrationRequest req) {
        String adminBearer = tokenProvider.adminBearer();

        UserResponse user = userClient.createUser(adminBearer, UserCreateRequest.builder()
                .name(req.getName())
                .surname(req.getSurname())
                .birthDate(req.getBirthDate())
                .email(req.getEmail())
                .active(true)
                .build());

        try {
            authClient.register(RegisterInternalRequest.builder()
                    .login(req.getLogin())
                    .password(req.getPassword())
                    .role("USER")
                    .userId(user.getId())
                    .build());
            return user;
        } catch (Exception e) {
            log.warn("Auth failed for {}, rolling back user {}", req.getLogin(), user.getId());
            try {
                userClient.deleteUser(adminBearer, user.getId());
            } catch (Exception rollbackErr) {
                log.error("Rollback failed for user {}", user.getId(), rollbackErr);
            }
            throw new RuntimeException("Registration failed: " + e.getMessage(), e);
        }
    }
}
