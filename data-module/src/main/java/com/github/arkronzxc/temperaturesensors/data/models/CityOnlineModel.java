package com.github.arkronzxc.temperaturesensors.data.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityOnlineModel extends Weather {
    private Long cod;
    private City.Coordinates coord;
    private String base;
    private Long timezone;
    private Long id;
    private String name;
}
