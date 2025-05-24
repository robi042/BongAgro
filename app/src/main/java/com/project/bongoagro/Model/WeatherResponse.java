package com.project.bongoagro.Model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {
    @SerializedName("location")
    private Location location;

    @SerializedName("current")
    private CurrentWeather current;

    @SerializedName("forecast")
    private Forecast forecast;

    public WeatherResponse(Location location, CurrentWeather current, Forecast forecast) {
        this.location = location;
        this.current = current;
        this.forecast = forecast;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CurrentWeather getCurrent() {
        return current;
    }

    public void setCurrent(CurrentWeather current) {
        this.current = current;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public static class Location {
        private String name;
        private String region;
        private String country;
        private double lat;
        private double lon;
        @SerializedName("tz_id")
        private String timezone;
        @SerializedName("localtime")
        private String localTime;

        public Location(String name, String region, String country, double lat, double lon, String timezone, String localTime) {
            this.name = name;
            this.region = region;
            this.country = country;
            this.lat = lat;
            this.lon = lon;
            this.timezone = timezone;
            this.localTime = localTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getLocalTime() {
            return localTime;
        }

        public void setLocalTime(String localTime) {
            this.localTime = localTime;
        }
    }

    public static class CurrentWeather {
        @SerializedName("temp_c")
        private double temperatureC;

        @SerializedName("temp_f")
        private double temperatureF;

        @SerializedName("humidity")
        private int humidity;

        @SerializedName("wind_kph")
        private double windSpeedKph;

        @SerializedName("condition")
        private Condition condition;

        public CurrentWeather(double temperatureC, double temperatureF, int humidity, double windSpeedKph, Condition condition) {
            this.temperatureC = temperatureC;
            this.temperatureF = temperatureF;
            this.humidity = humidity;
            this.windSpeedKph = windSpeedKph;
            this.condition = condition;
        }

        public double getTemperatureC() {
            return temperatureC;
        }

        public void setTemperatureC(double temperatureC) {
            this.temperatureC = temperatureC;
        }

        public double getTemperatureF() {
            return temperatureF;
        }

        public void setTemperatureF(double temperatureF) {
            this.temperatureF = temperatureF;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public double getWindSpeedKph() {
            return windSpeedKph;
        }

        public void setWindSpeedKph(double windSpeedKph) {
            this.windSpeedKph = windSpeedKph;
        }

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }
    }

    public static class Condition {
        private String text;
        private String icon;

        public Condition(String text, String icon) {
            this.text = text;
            this.icon = icon;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }
    }

    public static class Forecast {
        @SerializedName("forecastday")
        private List<ForecastDay> forecastDays;

        public Forecast(List<ForecastDay> forecastDays) {
            this.forecastDays = forecastDays;
        }

        public List<ForecastDay> getForecastDays() {
            return forecastDays;
        }

        public void setForecastDays(List<ForecastDay> forecastDays) {
            this.forecastDays = forecastDays;
        }
    }

    public static class ForecastDay {
        private String date;
        @SerializedName("day")
        private DayWeather dayWeather;
        @SerializedName("astro")
        private Astro astro;

        public ForecastDay(String date, DayWeather dayWeather, Astro astro) {
            this.date = date;
            this.dayWeather = dayWeather;
            this.astro = astro;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public DayWeather getDayWeather() {
            return dayWeather;
        }

        public void setDayWeather(DayWeather dayWeather) {
            this.dayWeather = dayWeather;
        }

        public Astro getAstro() {
            return astro;
        }

        public void setAstro(Astro astro) {
            this.astro = astro;
        }
    }

    public static class DayWeather {
        @SerializedName("maxtemp_c")
        private double maxTempC;

        @SerializedName("mintemp_c")
        private double minTempC;

        @SerializedName("avgtemp_c")
        private double avgTempC;

        @SerializedName("condition")
        private Condition condition;

        public DayWeather(double maxTempC, double minTempC, double avgTempC, Condition condition) {
            this.maxTempC = maxTempC;
            this.minTempC = minTempC;
            this.avgTempC = avgTempC;
            this.condition = condition;
        }

        public double getMaxTempC() {
            return maxTempC;
        }

        public void setMaxTempC(double maxTempC) {
            this.maxTempC = maxTempC;
        }

        public double getMinTempC() {
            return minTempC;
        }

        public void setMinTempC(double minTempC) {
            this.minTempC = minTempC;
        }

        public double getAvgTempC() {
            return avgTempC;
        }

        public void setAvgTempC(double avgTempC) {
            this.avgTempC = avgTempC;
        }

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }
    }

    public static class Astro {
        private String sunrise;
        private String sunset;

        public Astro(String sunrise, String sunset) {
            this.sunrise = sunrise;
            this.sunset = sunset;
        }

        public String getSunrise() {
            return sunrise;
        }

        public void setSunrise(String sunrise) {
            this.sunrise = sunrise;
        }

        public String getSunset() {
            return sunset;
        }

        public void setSunset(String sunset) {
            this.sunset = sunset;
        }
    }
}
