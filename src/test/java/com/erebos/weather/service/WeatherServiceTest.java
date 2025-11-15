package com.erebos.weather.service;

import com.erebos.weather.dto.CityTemperatureProjection;
import com.erebos.weather.repository.WeatherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class WeatherServiceTest {
    @ExtendWith(MockitoExtension.class)
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
        assertEquals("Montreal",city.city());
    }
}