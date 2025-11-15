package com.erebos.weather.repository; // Note: Package updated to match your error trace

import com.erebos.weather.dto.CityTemperatureProjection;
import com.erebos.weather.model.WeatherRecord;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

// MANDATORY: The generics must be defined here: <DocumentType, IdType>
@Repository
public interface WeatherRepository extends MongoRepository<WeatherRecord, String> {

    @Aggregation(pipeline = {
            "{$match: { 'province': ?0, 'date': ?1 }}",
            "{$sort: { 'max_temp': -1 }}",
            "{$limit: 1}",
            "{$project: { 'city': '$city', 'max_temp': '$max_temp', '_id': 0 }}"
    })
    Optional<CityTemperatureProjection> getHottestCity(String province, LocalDate date);
}