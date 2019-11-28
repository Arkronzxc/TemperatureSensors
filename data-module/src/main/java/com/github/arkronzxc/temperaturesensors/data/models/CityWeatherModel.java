package com.github.arkronzxc.temperaturesensors.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "main_weather_model")
public class CityWeatherModel {

    @Id
    @Column(name = "main_id")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty
    private String cod;

    @JsonProperty
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Weather> list;

    @JsonProperty
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id")
    private City city;
}
