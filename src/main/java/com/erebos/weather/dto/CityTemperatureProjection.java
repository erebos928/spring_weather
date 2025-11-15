package com.erebos.weather.dto;

/**
 * A Java Record used as a Projection (DTO) for the DAO query result.
 * It only contains the specific data needed (City Name and the Temperature).
 */
public record CityTemperatureProjection(
        String city,
        double max_temp
) {
    // Records automatically provide constructor, getters, hashCode, equals, and toString.
}