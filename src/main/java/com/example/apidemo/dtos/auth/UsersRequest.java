package com.example.apidemo.dtos.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersRequest {
    private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String roles;
}
