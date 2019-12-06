package com.github.arkronzxc.temperaturesensors.auth.util;

import com.github.arkronzxc.temperaturesensors.data.models.UserModel;
import com.github.arkronzxc.temperaturesensors.data.models.UserRegistrationModel;
import com.github.arkronzxc.temperaturesensors.data.repositories.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DbInit implements CommandLineRunner {

    private final UserModelRepository repository;
    private final PasswordEncoder passwordEncoder;

    public DbInit(UserModelRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new UserModel(new UserRegistrationModel("admin@gmail.com", "admin", passwordEncoder.encode("admin"))));
    }
}
