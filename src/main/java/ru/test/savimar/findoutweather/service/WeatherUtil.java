package ru.test.savimar.findoutweather.service;

import com.github.kevinsawicki.http.HttpRequest;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.test.savimar.findoutweather.json.JsonUtil;

import java.net.MalformedURLException;
import java.net.URL;


@Service
public class WeatherUtil {


    private static final Logger LOG = Logger.getLogger(WeatherUtil.class);

    public String getWeatherByCity(String city) {
        URL url = null;
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/forecast?q=" + city + "&units=metric&APPID=321327410d7438a508de3297727018b2");

        } catch (MalformedURLException e) {
            LOG.error("Не правильный формат url", e);
        }
        return getWeather(url);
    }

    public String getWeatherByGeo(String longitude, String latitude) {
        URL url = null;
        try {
            //    api.openweathermap.org/data/2.5/weather?lat=35&lon=139
            url = new URL("http://api.openweathermap.org/data/2.5/forecast?lat=" + latitude + "&lon=" + longitude + "&units=metric&APPID=321327410d7438a508de3297727018b2");
            //  http://api.openweathermap.org/data/2.5/forecast?lat=35&lon=139&units=metric&APPID=321327410d7438a508de3297727018b2

        } catch (MalformedURLException e) {
            LOG.error("Не правильный формат url", e);
        }
        return getWeather(url);
    }

    private synchronized String getWeather(URL url) {

        String json = null;
        try {
            json = com.github.kevinsawicki.http.HttpRequest.get(url).body();
        } catch (HttpRequest.HttpRequestException e) {
            LOG.error("Не доступно интернет-соединение", e);
        }

        String result = null;
        try {
            result = JsonUtil.parseJson(json);
        } catch (Exception e) {
            LOG.error("Не правильный формат json", e);
        }

        return result;
    }

}
