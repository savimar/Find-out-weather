package ru.test.savimar.findoutweather;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.test.savimar.findoutweather.service.WeatherUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindOutWeatherApplicationTests {

    @Autowired
    WeatherUtil weatherUtil;

    @Test
    public void contextLoads() {
    }

    @Test
    public void getWeather() {
        String json = weatherUtil.parseUrl("London");
        String[] weather = json.split(";");
        Assert.assertEquals(weather[5], "GB");
    }
}
