package com.example.weather;

public class WeatherData {
    private String city;
    private double temp;
    private double tempMax;
    private double tempMin;
    private double humidity;
    private double precip;
    private double windSpeed;
    private String windDir;
    private String conditions;

    public WeatherData(String city, double temp, double tempMax, double tempMin,
                        double humidity, double precip, double windSpeed, String windDir, String conditions) {
        this.city = city;
        this.temp = temp;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.humidity = humidity;
        this.precip = precip;
        this.windSpeed = windSpeed;
        this.windDir = windDir;
        this.conditions = conditions;
    }

    @Override
    public String toString() {
        return String.format(
                "\n--- Clima em %s ---\n" +
                "Temperatura atual: %.1f°C\n" +
                "Máxima: %.1f°C\n" +
                "Mínima: %.1f°C\n" +
                "Umidade: %.0f%%\n" +
                "Condições: %s\n" +
                "Precipitação: %.1f mm\n" +
                "Vento: %.1f km/h - Direção: %s\n",
                city, temp, tempMax, tempMin, humidity, conditions, precip, windSpeed, windDir
        );
    }

    public double getTemp() {
        return temp;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getConditions() {
        return conditions;
    }
}
