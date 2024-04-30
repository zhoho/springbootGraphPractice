package com.example.springGraph.repository;

import com.example.springGraph.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    @Query("SELECT w FROM Weather w ORDER BY w.id DESC")
    Optional<Weather> findLatestWeather();
}

