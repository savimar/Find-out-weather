package ru.test.savimar.findoutweather.service;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.test.savimar.findoutweather.json.JsonUtil;

import java.net.URI;


@Service
public class WeatherService {

    private static final Logger LOG = Logger.getLogger(WeatherService.class);
    private final String COMMON_URL = "http://api.openweathermap.org/data/2.5/forecast?";
    private final String APPID = "&units=metric&APPID=321327410d7438a508de3297727018b2";
    @Autowired
    JsonUtil jsonUtil;

    public String getWeatherByCity(String city) {
        return getWeather(COMMON_URL + "q=" + city + APPID);
    }

    public String getWeatherByGeo(String longitude, String latitude) {
        return getWeather(COMMON_URL + "lat=" + latitude + "&lon=" + longitude + APPID);
    }

    private String getWeather(String url) {

        String json = null;

        RestTemplate restTemplate = new RestTemplate();
        try {
            json = restTemplate.getForObject(new URI(url), String.class);

        } catch (Exception e) {
            LOG.error("Invalid format URI");
        }

        String result = null;
        try {
            result = jsonUtil.parseJson(json);
        } catch (Exception e) {
            LOG.error("Invalid format json", e);
        }

        return result;
    }

}
