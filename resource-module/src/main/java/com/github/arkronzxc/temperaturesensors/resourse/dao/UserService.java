package com.github.arkronzxc.temperaturesensors.resourse.dao;

import com.github.arkronzxc.temperaturesensors.data.exceptions.CustomWebException;
import com.github.arkronzxc.temperaturesensors.data.models.UserModel;
import com.github.arkronzxc.temperaturesensors.data.models.UserRegistrationModel;
import com.github.arkronzxc.temperaturesensors.data.repositories.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserModelRepository userModelRepository;

    public UserModel getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userModelRepository
                .findByUsername(username)
                .orElseThrow(() -> new CustomWebException("User is incorrect", HttpStatus.NOT_FOUND));
    }

    public void saveNewUser(UserRegistrationModel model){
        userModelRepository.save(new UserModel(model));
    }
}
