package ru.test.savimar.findoutweather.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import ru.test.savimar.findoutweather.service.WeatherUtil;

import java.time.LocalDateTime;


@Controller
public class CurrentWeatherController extends WebMvcConfigurerAdapter {

    private static final Logger LOG = Logger.getLogger(CurrentWeatherController.class);
    @Autowired
    WeatherUtil weatherUtil;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/resources/templates").setViewName("index");
    }


    @RequestMapping("/")
    public String index(Model model) {

        return "index";
    }

    @RequestMapping(value = "/showWeather", method = RequestMethod.POST)
    public ModelAndView getSity(@RequestParam String city, @RequestParam String user) {
        String text;
        ModelAndView modelAndView = new ModelAndView();
        if (city == null || city.trim().equals("")) {
            text = LocalDateTime.now() + " Поле 'город' пустое";
            getException(modelAndView, null, text);
        } else if (user == null || user.trim().equals("")) {
            text = LocalDateTime.now() + " Имя пользователя пустое";
            getException(modelAndView, null, text);
        } else {
            LOG.info("User " + user + " come " + LocalDateTime.now());


            String result;
            result = weatherUtil.parseUrl(city);

            if (result != null) {
                String[] weathers = result.split(";");
                if (weathers.length > 0) {

                    modelAndView.addObject("city", city);
                    modelAndView.setViewName("weather");


                    modelAndView.addObject("temp", weathers[0]);
                    modelAndView.addObject("pressure", weathers[1]);
                    modelAndView.addObject("humidity", weathers[2]);
                    modelAndView.addObject("rain", weathers[4]);
                    modelAndView.addObject("country", weathers[5]);

                    LOG.info(LocalDateTime.now() + " User " + user + " ask weather  to city " + city + " and get data: " + result);
                } else {
                    text = LocalDateTime.now() + " Пользователь " + user + " спросил погоду по городу " + city + ", но такой город не найден";
                    getException(modelAndView, null, text);
                    LOG.error(text);
                }

            } else {
                text = LocalDateTime.now() + " Пользователь " + user + " спросил погоду по городу " + city + ", данных не найдено";
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
