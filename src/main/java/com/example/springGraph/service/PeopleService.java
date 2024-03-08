package com.example.springGraph.service;

import com.example.springGraph.entity.People;
import com.example.springGraph.repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    // 추가적인 비즈니스 로직 메서드 구현
}
