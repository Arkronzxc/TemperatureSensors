package com.github.arkronzxc.temperaturesensors.resourse.controllers;

import com.github.arkronzxc.temperaturesensors.data.models.UserModel;
import com.github.arkronzxc.temperaturesensors.data.models.UserRegistrationModel;
import com.github.arkronzxc.temperaturesensors.resourse.dao.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping
    public ResponseEntity<Void> saveNewUser(@RequestBody UserRegistrationModel model){
        userService.saveNewUser(model);
        return ResponseEntity.ok().build();
    }

    @GetMapping("current")
    public ResponseEntity<UserModel> getCurrentUser(){
        return ResponseEntity.ok(userService.getCurrentUser());
    }
}
