package com.github.arkronzxc.temperaturesensors.resourse.repositories;

import com.github.arkronzxc.temperaturesensors.resourse.models.CityWeatherModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityWeatherRepository extends JpaRepository<CityWeatherModel, Long> {
    Optional<CityWeatherModel> findByCity_Name(String name);
}
