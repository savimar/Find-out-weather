package ru.test.savimar.findoutweather.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.log4j.Logger;
import ru.test.savimar.findoutweather.model.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class WeatherDeserializer extends JsonDeserializer<Weather> {
    private static final Logger LOG = Logger.getLogger(WeatherDeserializer.class);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public Weather deserialize(JsonParser jp, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        Weather weather = new Weather();
        JsonNode node = jp.getCodec().readTree(jp);

        JsonNode dataNode = node.get("list").get(0);

        JsonNode main = dataNode.get("main");
        Data data = new Data();
        DataMain dataMain = new DataMain();
        dataMain.setTemp(main.get("temp").asText());
        dataMain.setPressure(main.get("pressure").asText());
        dataMain.setHumidity(main.get("humidity").asText());

        data.setDataMain(dataMain);

        JsonNode dataWeather = dataNode.get("weather").get(0);
        Precipitation precipitation = new Precipitation();
        precipitation.setMain(dataWeather.get("main").asText());
        precipitation.setDescription(dataWeather.get("description").asText());

        data.setPrecipitation(precipitation);

        data.setDate(LocalDateTime.parse(dataNode.get("dt_txt").asText(), formatter));

        weather.setData(data);

        City city = new City();
        JsonNode dataCity = node.get("city");
        if (Objects.nonNull(dataCity.get("country"))) {
            city.setCountry(dataCity.get("country").asText());
        }
        if (Objects.nonNull(dataCity.get("name"))) {
            city.setName(dataCity.get("name").asText());
        }

        weather.setCity(city);

        LOG.info(weather.toString());
        return weather;
    }
}

