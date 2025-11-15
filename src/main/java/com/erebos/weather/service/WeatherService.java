package com.erebos.weather.service;

import com.erebos.weather.dto.CityTemperatureProjection;
import com.erebos.weather.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
@Service
public class WeatherService {
    @Autowired
    WeatherRepository weatherRepository;
    public CityTemperatureProjection getHottestCity(String province, LocalDate date){
        Optional<CityTemperatureProjection> hottestCity = weatherRepository.getHottestCity(province, date);
        return hottestCity.orElse(null);
    }
}
