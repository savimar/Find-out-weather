package ru.test.savimar.findoutweather.json;


import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.test.savimar.findoutweather.model.Weather;

import java.io.IOException;

@Service
public class JsonUtil {

    @Autowired
    WeatherObjectMapper mapper;

    private static final Logger LOG = Logger.getLogger(JsonUtil.class);

    public Weather parseJson(String json) {
        System.setProperty("file.encoding", "UTF-8");
        Weather weather = null;

        SimpleModule module = new SimpleModule();
        module.addDeserializer(Weather.class, new WeatherDeserializer());
        mapper.registerModule(module);

        LOG.info("JSON: " + json);
        try {
            weather = mapper.readValue(json, Weather.class);

        } catch (JsonParseException | JsonMappingException e) {
            LOG.error("Not valid  Json", e);
        } catch (IOException e) {
            LOG.error("IO exception", e);
        }
        return weather;
    }
}