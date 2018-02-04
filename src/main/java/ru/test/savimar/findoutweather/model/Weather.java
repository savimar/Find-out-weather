package ru.test.savimar.findoutweather.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.test.savimar.findoutweather.json.WeatherDeserializer;

@JsonDeserialize(using = WeatherDeserializer.class)
public class Weather {
    private Data data;
    private City city;

    @Override
    public String toString() {
        return "Weather{" +
                "data=" + data.toString() +
                ", city=" + city.toString() +
                '}';
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }


    public static class City {

        private String name;
        private String country;

        @Override
        public String toString() {
            return "City{" +
                    "name='" + name + '\'' +
                    ", country='" + country + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
