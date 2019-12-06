package com.github.arkronzxc.temperaturesensors.data.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@AllArgsConstructor
public class UserRegistrationModel {
    @Email
    private String email;

    private String username;
    private String password;
}
