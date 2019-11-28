package com.github.arkronzxc.temperaturesensors.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@SuppressWarnings("WeakerAccess")
@Entity
@Table(name = "city")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class City {

    @Id
    @Column(name = "city_id")
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("id")
    @Column(name = "json_city_id")
    private Long cityId;
    @Column
    @JsonProperty
    private String name;

    @JsonProperty
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "coords_id")
    private Coordinates coord;

    @JsonProperty
    @Column
    private String country;

    @JsonProperty
    @Column
    private Long population;

    @JsonProperty
    @Column
    private Long timezone;

    @JsonProperty
    @Column
    private Long sunrise;
    @JsonProperty
    @Column
    private Long sunset;

    @Entity
    @Table(name = "coords")
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    static final class Coordinates {

        @Id
        @Column(name = "coords_id")
        @JsonIgnore
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column
        @JsonProperty("lat")
        private Double latitude;

        @Column
        @JsonProperty("lon")
        private Double longitude;
    }

}
