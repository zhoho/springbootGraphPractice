package com.example.springGraph.service;

import com.example.springGraph.entity.Weather;
import com.example.springGraph.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    public Weather getWeather(String gridX, String gridY) {
        Weather weather = new Weather();
        weather.setGridX(gridX);
        weather.setGridY(gridY);
        weather.setTemperature(weather.getTemperature());
        weather.setSky(weather.getSky());
        weather.setPrecipitation(weather.getPrecipitation());
        weather.setTime(weather.getTime());

        return weather;
    }

}
