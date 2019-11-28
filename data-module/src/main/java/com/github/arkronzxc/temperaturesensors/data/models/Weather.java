package com.github.arkronzxc.temperaturesensors.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "weather")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Weather {

    @Id
    @Column(name = "weather_id")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @JsonProperty("dt")
    protected Long dataTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "main_weather_id")
    @JsonProperty
    protected MainWeather main;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("weather")
    protected List<WeatherDescription> weatherInfo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "clouds_id")
    @JsonProperty
    protected Clouds clouds;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "wind_id")
    @JsonProperty
    protected Wind wind;

    @Column
    @JsonProperty("dt_txt")
    protected String dataTimeFull;

    @Entity
    @Table(name = "main_weather_info")
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static final class MainWeather {

        @Id
        @Column(name = "main_weather_id")
        @JsonIgnore
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        @JsonProperty("temp")
        private Double temperature;

        @Column
        @JsonProperty("temp_min")
        private Double minTemperature;

        @Column
        @JsonProperty("temp_max")
        private Double maxTemperature;

        @Column
        @JsonProperty
        private Double pressure;

        @Column
        @JsonProperty
        private Double humidity;
    }

    @Entity
    @Table(name = "weather_desc")
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static final class WeatherDescription {

        @Id
        @Column(name = "weather_description_id")
        @JsonIgnore
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        @JsonProperty("id")
        private Long weatherDescId;

        @Column
        @JsonProperty("main")
        private String condition;

        @Column
        @JsonProperty
        private String description;
    }

    @Entity
    @Table(name = "clouds")
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static final class Clouds {

        @Id
        @Column(name = "clouds_id")
        @JsonIgnore
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "all_clouds")
        @JsonProperty
        private Long all;
    }

    @Entity
    @Table(name = "wind")
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    private static final class Wind {

        @Id
        @Column(name = "wind_id")
        @JsonIgnore
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        @JsonProperty
        private Double speed;

        @Column
        @JsonProperty("deg")
        private Double degree;
    }
}
