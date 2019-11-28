package com.github.arkronzxc.temperaturesensors.resourse.apiclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.arkronzxc.temperaturesensors.data.exceptions.CustomWebException;
import com.github.arkronzxc.temperaturesensors.data.models.CityOnlineModel;
import com.github.arkronzxc.temperaturesensors.data.models.CityWeatherModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Component
public class CityApiClient {

    private final String baseUrl;

    private final String forecastUrl;
    private final String apiKey;
    private final String onlineWeatherUrl;

    private final ObjectMapper objectMapper;

    private final CloseableHttpClient httpClient;

    public CityApiClient(ObjectMapper objectMapper,
                         @Value("${api.openweathermap.url.base}") final String baseUrl,
                         @Value("${api.openweathermap.url.forecast}") final String forecastUrl,
                         @Value("${api.openweathermap.url.online}") final String onlineWeatherUrl,
                         @Value("${api.openweathermap.key}") final String apiKey) {
        this.baseUrl = baseUrl;
        this.forecastUrl = forecastUrl;
        this.onlineWeatherUrl = onlineWeatherUrl;
        this.apiKey = apiKey;
        this.objectMapper = objectMapper;
        this.httpClient = HttpClients.createDefault();
    }

    public CityWeatherModel getCityTemperatureInfo(final String cityName, final String countryName)
            throws URISyntaxException, IOException {
        String hostName = baseUrl + forecastUrl;
        String rowQuery = createRowQuery(cityName, countryName);
        return simpleGetCityInfo(hostName, rowQuery, CityWeatherModel.class);
    }

    public CityOnlineModel getOnlineCityInfo(String cityName, String countryName)
            throws URISyntaxException, IOException {
       String hostName = baseUrl + onlineWeatherUrl;
       String rowQuery = createRowQuery(cityName, countryName);
       return simpleGetCityInfo(hostName, rowQuery, CityOnlineModel.class);
    }

    private <T> T simpleGetCityInfo(String hostName, String rowQuery, Class<T> valueType) throws URISyntaxException, IOException {
        URI uri = new URIBuilder()
                .setScheme("https")
                .setHost(hostName)
                .setCustomQuery(rowQuery)
                .build();
        log.info("URI " + uri);
        HttpGet request = new HttpGet(uri);
        CloseableHttpResponse response = httpClient.execute(request);
        InputStream inputStream = response.getEntity().getContent();
        if(response.getStatusLine().getStatusCode() != HttpStatus.OK.value()){
            throw new CustomWebException("Weather API response is incorrect ",
                    HttpStatus.valueOf(response.getStatusLine().getStatusCode()));
        }
        return objectMapper.readValue(inputStream, valueType);
    }

    private String createRowQuery(String cityName, String countryName){
        return "q=" + cityName + "," + countryName + "&appid=" + apiKey + "&units=metric";
    }
}


