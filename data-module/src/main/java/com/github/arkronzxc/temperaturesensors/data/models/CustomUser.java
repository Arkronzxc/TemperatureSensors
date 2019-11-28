package com.github.arkronzxc.temperaturesensors.data.models;

import org.springframework.security.core.userdetails.User;

import java.util.List;

public class CustomUser extends User {
    private static final long serialVersionUID = 1223334444L;

    public CustomUser(UserModel userModel) {
        super(userModel.getUsername(), userModel.getPassword(), List.of(userModel.getAuthority()));
    }
}
