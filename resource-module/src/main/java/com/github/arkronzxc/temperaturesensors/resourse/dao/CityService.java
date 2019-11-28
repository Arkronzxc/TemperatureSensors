package com.github.arkronzxc.temperaturesensors.resourse.dao;

import com.github.arkronzxc.temperaturesensors.resourse.apiclient.CityApiClient;
import com.github.arkronzxc.temperaturesensors.resourse.exceptions.CustomWebException;
import com.github.arkronzxc.temperaturesensors.resourse.models.CityOnlineModel;
import com.github.arkronzxc.temperaturesensors.resourse.models.CityWeatherModel;
import com.github.arkronzxc.temperaturesensors.resourse.repositories.CityWeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;

@Service
@Slf4j
public class CityService {

    private final CityWeatherRepository repository;

    private final CityApiClient cityApiClient;

    public CityService(CityApiClient cityApiClient, CityWeatherRepository repository) {
        this.cityApiClient = cityApiClient;
        this.repository = repository;
    }


    public CityWeatherModel getWeatherModel(String cityName) {
        return repository.findByCity_Name(cityName)
                .orElseGet(() -> {
                    try {
                        return repository.save(cityApiClient.getCityTemperatureInfo(cityName, "ru"));
                    } catch (URISyntaxException e) {
                        log.error("URI issues", e);
                        throw new CustomWebException("URI issues", HttpStatus.BAD_REQUEST);
                    } catch (IOException e) {
                        log.error("Error", e);
                        throw new CustomWebException(HttpStatus.INTERNAL_SERVER_ERROR, e);
                    }
                });
    }

    public CityOnlineModel getOnlineCityInfo(String cityName) {
        try {
            return cityApiClient.getOnlineCityInfo(cityName, "ru");
        } catch (URISyntaxException e) {
            log.error("URI issues", e);
            throw new CustomWebException("URI issues", HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            log.error("Error", e);
            throw new CustomWebException(HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
}

