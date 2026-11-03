package com.innowise.apigateway.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private Boolean active;
}
