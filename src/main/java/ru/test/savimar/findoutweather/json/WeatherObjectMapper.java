package ru.test.savimar.findoutweather.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.test.savimar.findoutweather.model.Weather;

@Component
@Primary
public class WeatherObjectMapper extends ObjectMapper {

    public WeatherObjectMapper() {
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Weather.class, new WeatherDeserializer());
        this.registerModule(module);
    }


}