package com.erebos.weather.service;

import com.erebos.weather.dto.CityTemperatureProjection;
import com.erebos.weather.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

class WeatherServiceTest {
    @Mock
    WeatherRepository weatherRepository;
    @InjectMocks
    WeatherService weatherService;
    CityTemperatureProjection mockProjection = new CityTemperatureProjection("Montreal", 15.0);
    @Test
    void getHottestCity_souldReturnCity_whenFound(){
        when(weatherRepository.getHottestCity("QC",LocalDate.of(2025,9,9))).
                thenReturn(Optional.of(mockProjection));
        CityTemperatureProjection city = weatherService.getHottestCity("QC", LocalDate.of(2025, 9, 9));

    }
}