package com.erebos.weather.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/**
 * MongoDB Document representing a daily weather observation for a city.
 * This object is saved directly to and retrieved from the database.
 */
@Document(collection = "temperatures")
public class WeatherRecord {

    @Id
    private String id;
    private String city;
    private String province;
    private LocalDate date;
    private double max_temp; // The value we query against

    // Standard constructor, getters, and setters (omitted for brevity in DTO/Record files)

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCityName() { return city; }
    public void setCity(String cityName) { this.city = cityName; }
    public String getProvince() { return province; }
    public void setProvince(String province) { this.province = province; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public double getMax_temp() { return max_temp; }
    public void setMax_temp(double max_temp) { this.max_temp = max_temp; }

    // toString() implementation for logging
    @Override
    public String toString() {
        return "City{" +
                "cityName='" + city + '\'' +
                ", province='" + province + '\'' +
                ", date=" + date +
                ", maxTemperature=" + max_temp +
                '}';
    }
}