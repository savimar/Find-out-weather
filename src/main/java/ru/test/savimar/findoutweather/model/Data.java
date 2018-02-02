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
}
