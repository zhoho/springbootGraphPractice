package com.example.springGraph.service;

import com.example.springGraph.entity.Weather;
import com.example.springGraph.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;
    private final String apiUrl = "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
    private final String serviceKey = "hogAXADhx1TwUFYu9kBfwwvsv5m%2FwsLzvQT2tBT0BFSH3eLnXp88Dx%2FR6x2GCPtWKfaUQEPix2VcWYEp0Hz5Tw%3D%3D";

    @Scheduled(fixedRate = 3600000)  // 1 hour interval
    public void fetchAndStoreWeatherData() {

        String gridX = "60";  // 예시 좌표
        String gridY = "127";
        LocalDateTime now = LocalDateTime.now();
        String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = getNextForecastTime(now);

        String queryParams = String.format("?serviceKey=%s&pageNo=1&numOfRows=37&dataType=XML&base_date=%s&base_time=%s&nx=%s&ny=%s",
                serviceKey, baseDate, baseTime, gridX, gridY);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl + queryParams, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            Weather weather = parseWeatherData(response.getBody());
            weatherRepository.save(weather);
        }
    }

    private String getNextForecastTime(LocalDateTime now) {
        int hour = now.getHour();
        int nextForecastHour = (hour / 3) * 3 - 1;
        if (nextForecastHour <= 0) return "2300";
        return String.format("%02d00", nextForecastHour);
    }

    private Weather parseWeatherData(String xmlData) {

        Weather weather = new Weather();
        weather.setTemperature(weather.getTemperature());
        weather.setSky(weather.getSky());
        weather.setPrecipitation(weather.getPrecipitation());
        return weather;
    }

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

    public Weather getLatestWeather() {
        return weatherRepository.findTopByOrderByIdDesc();
    }
}
