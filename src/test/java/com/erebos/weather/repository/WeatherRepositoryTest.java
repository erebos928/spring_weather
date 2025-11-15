package com.erebos.weather.repository;

import com.erebos.weather.dto.CityTemperatureProjection;
import com.erebos.weather.model.WeatherRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// This annotation loads a test-specific Spring context for the MongoDB data layer
@DataMongoTest
@ActiveProfiles("test")
class WeatherRepositoryTest {

    // Inject the repository we are testing
    @Autowired
    WeatherRepository weatherRepository;

    // Inject MongoTemplate for manual setup/teardown of test data
    @Autowired
    MongoTemplate mongoTemplate;

    private final LocalDate TEST_DATE = LocalDate.of(2025, 11, 9);
    private final String TEST_PROVINCE = "QC";

    /**
     * Clears existing data and inserts necessary test data before each test.
     */
    @BeforeEach
    void setup() {
        // 1. Clean up before running the test
        mongoTemplate.getDb().drop();

        // 2. Insert test data for the query:
        // City 1 (The Hottest)
        WeatherRecord ahvaz = new WeatherRecord();
        ahvaz.setCity("Ahvaz");
        ahvaz.setProvince(TEST_PROVINCE);
        ahvaz.setDate(TEST_DATE);
        ahvaz.setMax_temp(45.0);
        weatherRepository.save(ahvaz);

        // City 2 (A Cooler City in the same group) - Ensures sorting works
        WeatherRecord montreal = new WeatherRecord();
        montreal.setCity("Montreal");
        montreal.setProvince(TEST_PROVINCE);
        montreal.setDate(TEST_DATE);
        montreal.setMax_temp(30.0);
        weatherRepository.save(montreal);

        // City 3 (A City from a different province) - Ensures matching works
        WeatherRecord calgary = new WeatherRecord();
        calgary.setCity("Calgary");
        calgary.setProvince("AB");
        calgary.setDate(TEST_DATE);
        calgary.setMax_temp(40.0);
        weatherRepository.save(calgary);
    }

    @Test
    void shouldReturnHottestCityForProvinceAndDate() {
        // ACT
        Optional<CityTemperatureProjection> result = weatherRepository.getHottestCity(TEST_PROVINCE, TEST_DATE);

        // ASSERT
        assertTrue(result.isPresent(), "Result should not be empty.");

        CityTemperatureProjection hottestCity = result.get();

        // Ensure the correct (hottest) city is returned
        assertEquals("Ahvaz", hottestCity.city(), "The city name should be Ahvaz.");
        assertEquals(45.0, hottestCity.max_temp(), 0.001, "The temperature should be 45.0.");
    }

    @Test
    void shouldReturnEmptyForNonExistentData() {
        // ACT
        Optional<CityTemperatureProjection> result = weatherRepository.getHottestCity("QC", LocalDate.of(2026, 1, 1));

        // ASSERT
        assertTrue(result.isEmpty(), "Result should be empty if the date is not found.");
    }
}