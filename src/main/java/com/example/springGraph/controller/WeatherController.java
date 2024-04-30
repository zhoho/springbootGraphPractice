package com.example.springGraph.controller;

import com.example.springGraph.entity.Location;
import com.example.springGraph.entity.Weather;
import com.example.springGraph.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public String showWeather(@RequestParam String gridX, @RequestParam String gridY, Model model) {
        Weather weather = weatherService.getWeather(gridX, gridY);
        model.addAttribute("weather", weather);
        return "weatherPage";  // Thymeleaf 템플릿 이름
    }
}
