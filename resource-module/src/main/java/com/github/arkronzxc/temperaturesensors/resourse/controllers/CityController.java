package com.github.arkronzxc.temperaturesensors.resourse.controllers;

import com.github.arkronzxc.temperaturesensors.resourse.dao.CityService;
import com.github.arkronzxc.temperaturesensors.resourse.models.CityOnlineModel;
import com.github.arkronzxc.temperaturesensors.resourse.models.CityWeatherModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;

@RestController
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("city/{cityName}")
    public ResponseEntity<CityWeatherModel> getWeatherInfo(@PathVariable String cityName) {
        return ResponseEntity.ok(cityService.getWeatherModel(cityName));
    }

    @GetMapping("city/online/{cityName}")
    public ResponseEntity<CityOnlineModel> getWeatherOnlineInfo(@PathVariable String cityName)
            throws IOException, URISyntaxException {
        return ResponseEntity.ok(cityService.getOnlineCityInfo(cityName));
    }
}
