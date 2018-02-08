package ru.test.savimar.findoutweather.controller;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.test.savimar.findoutweather.model.Weather;
import ru.test.savimar.findoutweather.service.WeatherService;

import java.time.LocalDateTime;
import java.util.Objects;


@Controller
public class CurrentWeatherController {

    private static final Logger LOG = Logger.getLogger(CurrentWeatherController.class);

    @Autowired
    WeatherService weatherService;




    @RequestMapping("/")
    public String index(Model model) {

        return "index";
    }

    @RequestMapping(value = "/showWeather", method = RequestMethod.POST)
    public ModelAndView getSity(@RequestParam String city, @RequestParam String user, @RequestParam String longitude, @RequestParam String latitude) {
        String text;
        ModelAndView modelAndView = new ModelAndView();
        if ((city.trim().equals("")) && (longitude == null || longitude.trim().equals("")) && (latitude == null || latitude.trim().equals(""))) {
            text = LocalDateTime.now() + " Введите город или данные геолокации";
            getException(modelAndView, null, text);
        } else if (!city.trim().equals("") && !longitude.trim().equals("") && !latitude.trim().equals("")) {
            text = LocalDateTime.now() + " Должны быть введены либо город, либо данные геолокации, но не все вместе";
            getException(modelAndView, null, text);
        } else if (user == null || user.trim().equals("")) {
            text = LocalDateTime.now() + " Имя пользователя пустое";
            getException(modelAndView, null, text);
        } else if (city.trim().equals("") && (!(NumberUtils.isNumber(latitude) && NumberUtils.isNumber(longitude)))) {
            text = LocalDateTime.now() + " В полях геолокации должны быть только числа";
            getException(modelAndView, null, text);
        } else {
            LOG.info("User " + user + " come " + LocalDateTime.now());


            Weather weather = null;
            if (!city.trim().equals("")) {
                weather = weatherService.getWeatherByCity(city);
            } else if (!(longitude.trim().equals("") && latitude.trim().equals(""))) {
                weather = weatherService.getWeatherByGeo(longitude, latitude);
            }


            if (weather != null) {
                if (Objects.nonNull(weather.getCity().getCountry())) {
                    modelAndView.addObject("country", weather.getCity().getCountry());
                } else {
                    modelAndView.addObject("country", "Страна не определена");
                    if (city.equals("")) {
                        city = Objects.nonNull(weather.getCity().getName()) ? weather.getCity().getName() : "Город не определен. Координаты: lon = " + longitude + ", lat = " + latitude;
                    }

                }

                modelAndView.addObject("city", city);
                modelAndView.setViewName("weather");


                modelAndView.addObject("temp", weather.getData().getDataMain().getTemp());
                modelAndView.addObject("pressure", weather.getData().getDataMain().getPressure());
                modelAndView.addObject("humidity", weather.getData().getDataMain().getHumidity());
                modelAndView.addObject("rain", weather.getData().getPrecipitation().getDescription());
                modelAndView.addObject("precipitation", weather.getData().getPrecipitation().getMain());

                LOG.info(LocalDateTime.now() + " User " + user + " ask weather  for city " + city + " and get data: " + weather.toString());
            } else {
                if (!city.trim().equals("")) {
                    text = LocalDateTime.now() + " User " + user + " ask weather  for city " + city + ", it not found";
                } else {
                    text = LocalDateTime.now() + " User " + user + " ask weather  by  geo: longitude" + longitude + " and latitude " + latitude + ", it not found";
                }
                getException(modelAndView, null, text);
                LOG.error(text);
            }
        }


        return modelAndView;
    }

    private void getException(ModelAndView modelAndView, Exception e, String text) {
        modelAndView.setViewName("exception");
        modelAndView.addObject("message", text);
        if (e != null) {
            LOG.error(text, e);
        } else {
            LOG.error(text);
        }

    }
}

