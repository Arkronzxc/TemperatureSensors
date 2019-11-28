package com.github.arkronzxc.temperaturesensors.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@EnableAuthorizationServer
@SpringBootApplication
@EnableJpaRepositories("com.github.arkronzxc.temperaturesensors.data.repositories")
@EntityScan("com.github.arkronzxc.temperaturesensors.data")
@ComponentScan({"com.github.arkronzxc.temperaturesensors.data", "com.github.arkronzxc.temperaturesensors.auth"})
public class AuthModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthModuleApplication.class);
    }
}
