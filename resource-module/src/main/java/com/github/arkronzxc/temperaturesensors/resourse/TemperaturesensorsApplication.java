package com.github.arkronzxc.temperaturesensors.resourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories("com.github.arkronzxc.temperaturesensors.data.repositories")
@EntityScan("com.github.arkronzxc.temperaturesensors.data")
@ComponentScan({"com.github.arkronzxc.temperaturesensors.data", "com.github.arkronzxc.temperaturesensors.resourse"})
public class TemperaturesensorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemperaturesensorsApplication.class, args);
    }

}
