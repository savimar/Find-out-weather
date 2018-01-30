package ru.test.savimar.findoutweather.json;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;
import java.util.StringJoiner;

@Service
public class JsonUtil {

    @Autowired
    WeatherObjectMapper mapper;

    private static final Logger LOG = Logger.getLogger(JsonUtil.class);

    public String parseJson(String json) {
        StringJoiner stringJoiner = new StringJoiner(";");
        System.setProperty("file.encoding", "UTF-8");
        ObjectNode rootNode;

        LOG.info("JSON: " + json);
        try {
            rootNode = mapper.readValue(json, ObjectNode.class);
            if (Objects.nonNull(rootNode.get("list").get(0))) {
                com.fasterxml.jackson.databind.JsonNode data = rootNode.get("list").get(0);

                JsonNode dataMain = data.get("main");
                stringJoiner.add(dataMain.get("temp").asText());
                stringJoiner.add(dataMain.get("pressure").asText());
                stringJoiner.add(dataMain.get("humidity").asText());

                JsonNode dataWeather = data.get("weather").get(0);
                stringJoiner.add(dataWeather.get("main").asText());
                stringJoiner.add(dataWeather.get("description").asText());

                JsonNode dataCity = rootNode.get("city");
                if (Objects.nonNull(dataCity.get("country"))) {
                    stringJoiner.add(dataCity.get("country").asText());
                }

                if (Objects.nonNull(dataCity.get("name"))) {
                    stringJoiner.add(dataCity.get("name").asText());
                }


            }
        } catch (JsonParseException | JsonMappingException e) {
            LOG.error("Неправильный формат Json", e);
        } catch (IOException e) {
            LOG.error("Ошибка ввода-вывода", e);
        }
        return stringJoiner.toString();
    }
}