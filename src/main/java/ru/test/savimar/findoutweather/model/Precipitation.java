package ru.test.savimar.findoutweather.model;

public class Precipitation {

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
