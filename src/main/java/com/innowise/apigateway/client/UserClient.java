package com.innowise.apigateway.client;

import com.innowise.apigateway.dto.UserCreateRequest;
import com.innowise.apigateway.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service", url = "${USER_SERVICE_URL:http://localhost:8081}")
public interface UserClient {
    @PostMapping("/api/users")
    UserResponse createUser(@RequestHeader("Authorization") String bearer,
                            @RequestBody UserCreateRequest request);

    @DeleteMapping("/api/users/{id}")
    void deleteUser(@RequestHeader("Authorization") String bearer,
                    @PathVariable("id") Long id);
}
