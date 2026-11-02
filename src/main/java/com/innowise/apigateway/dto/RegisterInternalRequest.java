package com.innowise.apigateway.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterInternalRequest {
    private String login;
    private String password;
    private String role;
    private Long userId;
}
