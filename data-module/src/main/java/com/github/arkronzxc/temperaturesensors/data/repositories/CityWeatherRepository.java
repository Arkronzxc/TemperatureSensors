package com.github.arkronzxc.temperaturesensors.data.repositories;

import com.github.arkronzxc.temperaturesensors.data.models.CityWeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityWeatherRepository extends JpaRepository<CityWeatherModel, Long> {
    Optional<CityWeatherModel> findByCity_Name(String name);
}
