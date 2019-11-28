package com.github.arkronzxc.temperaturesensors.data.models;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class UserRegistrationModel {
    @Email
    private String email;

    private String username;
    private String password;
}
