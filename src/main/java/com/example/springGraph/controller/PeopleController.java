package com.example.springGraph.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.springGraph.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
@RequestMapping("/index")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String getChartData(Model model) {
        Map<Integer, Long> ageDistribution = peopleService.getAgeDistribution();
        Long femaleCount = peopleService.getFemaleCount();
        Map<Integer, Long> femalesByAgeGroup = peopleService.countFemalesByAgeGroup(); // 연령대별 여성 수 추가


        model.addAttribute("ageDistribution", ageDistribution);
        model.addAttribute("femaleCount", femaleCount);
        model.addAttribute("femalesByAgeGroup", femalesByAgeGroup); // 모델에 연령대별 여성 수 추가

        return "index";  // Thymeleaf 템플릿 반환
    }
}
