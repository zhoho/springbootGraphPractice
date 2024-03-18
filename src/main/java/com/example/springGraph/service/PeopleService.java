package com.example.springGraph.service;

import com.example.springGraph.entity.People;
import com.example.springGraph.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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

    public Map<Integer, Long> initializeAgeDistribution() {
        Map<Integer, Long> ageDistribution = new TreeMap<>();
        for (int i = 0; i <= 90; i += 10) {
            ageDistribution.put(i, 0L); // Initialize age distribution map
        }
        return ageDistribution;
    }

    public Map<Integer, Long> getAgeDistribution() {
        return calculateAgeDistribution(null);
    }

    public Long getCountByGender(String gender) {
        return peopleRepository.findAll().stream()
                .filter(person -> gender == null || gender.equalsIgnoreCase(person.getGender()))
                .count();
    }

    public Map<Integer, Long> getAgeDistributionByGender(String gender) {
        return calculateAgeDistribution(gender);
    }

    private Map<Integer, Long> calculateAgeDistribution(String gender) {
        List<People> filteredList;
        if (gender != null && !gender.isEmpty()) {
            filteredList = peopleRepository.findAll().stream()
                    .filter(person -> gender.equalsIgnoreCase(person.getGender()))
                    .collect(Collectors.toList());
        } else {
            filteredList = peopleRepository.findAll();
        }

        Map<Integer, Long> ageDistribution = filteredList.stream()
                .collect(Collectors.groupingBy(
                        person -> (person.getAge() / 10) * 10,
                        TreeMap::new,
                        Collectors.counting()
                ));

        Map<Integer, Long> completeAgeDistribution = initializeAgeDistribution();
        completeAgeDistribution.forEach((age, count) -> ageDistribution.merge(age, count, Long::sum));
        return ageDistribution;
    }
}

