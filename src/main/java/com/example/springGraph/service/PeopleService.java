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
            ageDistribution.put(i, 0L); // 0부터 90까지 10단위로 초기화
        }
        return ageDistribution;
    }

    public Map<Integer, Long> getAgeDistribution() {
        List<People> peopleList = peopleRepository.findAll();
        Map<Integer, Long> ageDistribution = peopleList.stream()
                .collect(Collectors.groupingBy(person -> (person.getAge() / 10) * 10, // 연령대를 계산 (예: 25 -> 20, 37 -> 30)
                        Collectors.counting()));
        Map<Integer, Long> completeAgeDistribution = initializeAgeDistribution(); // 위에서 정의한 메소드 사용
        completeAgeDistribution.forEach((age, count) -> ageDistribution.merge(age, count, Long::sum));
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
                        person -> (person.getAge() / 10) * 10,
                        Collectors.counting()
                ));

        Map<Integer, Long> completeAgeDistribution = initializeAgeDistribution(); // 미리 정의된 연령대 분포 맵
        completeAgeDistribution.forEach((age, count) -> femalesCountByAgeGroup.merge(age, count, Long::sum));
        return femalesCountByAgeGroup;
    }

    public Map<Integer, Long> countMalesByAgeGroup() {
        List<People> peopleList = peopleRepository.findAll();
        Map<Integer, Long> malesCountByAgeGroup = peopleList.stream()
                .filter(person -> "Male".equals(person.getGender()))
                .collect(Collectors.groupingBy(
                        person -> (person.getAge() / 10) * 10,
                        Collectors.counting()
                ));

        Map<Integer, Long> completeAgeDistribution = initializeAgeDistribution(); // 미리 정의된 연령대 분포 맵
        completeAgeDistribution.forEach((age, count) -> malesCountByAgeGroup.merge(age, count, Long::sum));
        return malesCountByAgeGroup;
    }
}
