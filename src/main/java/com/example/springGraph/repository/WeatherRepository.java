package com.example.springGraph.repository;

import com.example.springGraph.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Weather findTopByOrderByIdDesc();
}
