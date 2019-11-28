package com.github.arkronzxc.temperaturesensors.resourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TemperaturesensorsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TemperaturesensorsApplication.class, args);
    }

}
