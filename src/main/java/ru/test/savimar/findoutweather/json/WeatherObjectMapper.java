package ru.test.savimar.findoutweather.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class WeatherObjectMapper extends ObjectMapper {
    public WeatherObjectMapper() {
        setSerializationInclusion(JsonInclude.Include.NON_NULL);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        /* configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);*/
    }
}