package com.example.springGraph.service;

import com.example.springGraph.entity.People;
import com.example.springGraph.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<People> getAllPeople() {
        return peopleRepository.findAll();
    }

    public Map<Integer, Long> getAgeDistribution() {
        List<People> peopleList = peopleRepository.findAll();
        Map<Integer, Long> ageDistribution = peopleList.stream()
                .collect(Collectors.groupingBy(person -> (person.getAge() / 10) * 10, // 연령대를 계산 (예: 25 -> 20, 37 -> 30)
                        Collectors.counting()));
        return ageDistribution;
    }


    public Long getFemaleCount() {
        List<People> peopleList = peopleRepository.findAll();
        Long femaleCount = peopleList.stream()
                .filter(person -> "Female".equals(person.getGender()))
                .count();
        return femaleCount;
    }

    public Map<Integer, Long> countFemalesByAgeGroup() {
        List<People> peopleList = peopleRepository.findAll();
        Map<Integer, Long> femalesCountByAgeGroup = peopleList.stream()
                .filter(person -> "Female".equals(person.getGender()))
                .collect(Collectors.groupingBy(
                        person -> (person.getAge() / 10) * 10, // 연령대를 계산 (예: 25 -> 20, 37 -> 30)
                        Collectors.counting() // 그룹별로 수를 센다
                ));
        return femalesCountByAgeGroup;
    }

    public Map<Integer, Long> countMalesByAgeGroup() {
        List<People> peopleList = peopleRepository.findAll();
        return peopleList.stream()
                .filter(person -> "Male".equals(person.getGender()))
                .collect(Collectors.groupingBy(
                        person -> (person.getAge() / 10) * 10,
                        Collectors.counting()
                ));
    }


}
