package com.innowise.apigateway.client;

import com.innowise.apigateway.dto.RegisterInternalRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "auth-service", url = "${AUTH_SERVICE_URL:http://localhost:8082}")
public interface AuthClient {
    @PostMapping("/api/auth/register")
    void register(@RequestBody RegisterInternalRequest request);
}
