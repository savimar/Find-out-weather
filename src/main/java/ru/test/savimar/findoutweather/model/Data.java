package ru.test.savimar.findoutweather.model;

import java.time.LocalDateTime;

public class Data {
    private DataMain dataMain;
    private Precipitation precipitation;
    private LocalDateTime date;

    @Override
    public String toString() {
        return "Data{" +
                "dataMain=" + dataMain.toString() +
                ", precipitation=" + precipitation.toString() +
                ", date=" + date +
                '}';
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public DataMain getDataMain() {
        return dataMain;
    }

    public void setDataMain(DataMain dataMain) {
        this.dataMain = dataMain;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Precipitation precipitation) {
        this.precipitation = precipitation;
    }


    public static class DataMain {
        private String temp;
        private String pressure;
        private String humidity;


        @Override
        public String toString() {
            return "DataMain{" +
                    "temp='" + temp + '\'' +
                    ", pressure='" + pressure + '\'' +
                    ", humidity='" + humidity + '\'' +
                    '}';
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public String getPressure() {
            return pressure;
        }

        public void setPressure(String pressure) {
            this.pressure = pressure;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }
    }


    public static class Precipitation {

        private String main;
        private String description;

        @Override
        public String toString() {
            return "Precipitation{" +
                    "main='" + main + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
