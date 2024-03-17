package com.example.springGraph.controller;

import com.example.springGraph.entity.People;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.springGraph.service.PeopleService;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/age-distribution")
    @ResponseBody
    public Map<Integer, Long> getAgeDistribution() {
        return peopleService.getAgeDistribution();
    }

    @GetMapping("/total-people")
    @ResponseBody
    public List<People> getTotalPeople() {
        return peopleService.getAllPeople();
    }

    @GetMapping("/female/count")
    @ResponseBody
    public Long getFemaleCount() {
        return peopleService.getFemaleCount();
    }

    @GetMapping("/female/age-distribution")
    @ResponseBody
    public Map<Integer, Long> getFemalesByAgeGroup() {
        return peopleService.countFemalesByAgeGroup();
    }

    // 이미 고유한 경로가 있으므로 변경할 필요가 없습니다.
    @GetMapping("/male/age-distribution")
    @ResponseBody
    public Map<Integer, Long> getMaleAgeDistribution() {
        return peopleService.countMalesByAgeGroup();
    }
}
