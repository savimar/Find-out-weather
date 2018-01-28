package ru.test.savimar.findoutweather.json;


import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;

import java.io.IOException;
import java.util.Objects;
import java.util.StringJoiner;

public class JsonUtil {

    private static final Logger LOG = Logger.getLogger(JsonUtil.class);

    public static String parseJson(String json) {
        StringJoiner stringJoiner = new StringJoiner(";");
        System.setProperty("file.encoding", "UTF-8");
        ObjectNode rootNode;
        ObjectMapper mapper = new ObjectMapper();
        try {
            rootNode = mapper.readValue(json, ObjectNode.class);
            if (Objects.nonNull(rootNode.get("list").get(0))) {
                JsonNode data = rootNode.get("list").get(0);

                JsonNode dataMain = data.get("main");
                stringJoiner.add(dataMain.get("temp").asText());
                stringJoiner.add(dataMain.get("pressure").asText());
                stringJoiner.add(dataMain.get("humidity").asText());

                JsonNode dataWeather = data.get("weather").get(0);
                stringJoiner.add(dataWeather.get("main").asText());
                stringJoiner.add(dataWeather.get("description").asText());
                stringJoiner.add(rootNode.get("city").get("country").asText());


            }
        } catch (JsonParseException | JsonMappingException e) {
            LOG.error("Неправильный формат Json", e);
        } catch (IOException e) {
            LOG.error("Ошибка ввода-вывода", e);
        }
        return stringJoiner.toString();
    }
}