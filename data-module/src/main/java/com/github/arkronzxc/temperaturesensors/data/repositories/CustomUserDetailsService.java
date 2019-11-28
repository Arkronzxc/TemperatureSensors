package com.github.arkronzxc.temperaturesensors.data.repositories;

import com.github.arkronzxc.temperaturesensors.data.exceptions.CustomWebException;
import com.github.arkronzxc.temperaturesensors.data.models.CustomUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserModelRepository userModelRepository;

    public CustomUserDetailsService(UserModelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new CustomUser(userModelRepository.findByUsername(username)
                .orElseThrow(() -> new CustomWebException("Can not find user " + username, HttpStatus.NOT_FOUND)));
    }
}
