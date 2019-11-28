package com.github.arkronzxc.temperaturesensors.resourse.dao.schedulers;

import com.github.arkronzxc.temperaturesensors.resourse.apiclient.CityApiClient;
import com.github.arkronzxc.temperaturesensors.data.models.CityWeatherModel;
import com.github.arkronzxc.temperaturesensors.data.models.repositories.CityWeatherRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;

@Slf4j
@EnableScheduling
@Component
public class CitiesScheduler {

    private final CityWeatherRepository weatherRepository;
    private final CityApiClient apiClient;

    public CitiesScheduler(CityWeatherRepository weatherRepository, CityApiClient apiClient) {
        this.weatherRepository = weatherRepository;
        this.apiClient = apiClient;
    }

    @Scheduled(cron = "0/10 0 * * * * ")
    public void scheduleUpdateCitiesData() {
        weatherRepository.findAll().parallelStream().forEach(cityWeatherModel -> {
            try {
                CityWeatherModel model = apiClient
                        .getCityTemperatureInfo(cityWeatherModel.getCity().getName(), "ru");
                if (!cityWeatherModel.equals(model) && cityWeatherModel.getCity().equals(model.getCity())) {
                    cityWeatherModel.setCod(model.getCod());
                    cityWeatherModel.setList(model.getList());
                    weatherRepository.save(cityWeatherModel);
                }

            } catch (URISyntaxException | IOException e) {
                log.error("Error", e);
            }
        });
    }
}
