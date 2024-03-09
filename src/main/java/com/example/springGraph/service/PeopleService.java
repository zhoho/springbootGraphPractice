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
                .collect(Collectors.groupingBy(People::getAge, Collectors.counting()));
        return ageDistribution;
    }


    public Long getFemaleCount() {
        List<People> peopleList = peopleRepository.findAll();
        Long femaleCount = peopleList.stream()
                .filter(person -> "Female".equals(person.getGender()))
                .count();
        return femaleCount;
    }


}
