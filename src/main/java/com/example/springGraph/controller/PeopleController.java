package com.example.springGraph.controller;

import com.example.springGraph.entity.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    // 성별에 따른 사람 수를 반환하는 경로
    @GetMapping("/people/count")
    @ResponseBody
    public Long getCountByGender(@RequestParam(required = false) String gender) {
        return peopleService.getCountByGender(gender);
    }

    // 성별에 따른 연령 분포를 반환하는 경로
    @GetMapping("/people/age-distribution")
    @ResponseBody
    public Map<Integer, Long> getAgeDistributionByGender(@RequestParam(required = false) String gender) {
        return peopleService.getAgeDistributionByGender(gender);
    }
}

