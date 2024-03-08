package com.example.springGraph.repository;

import com.example.springGraph.entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Long> {
}

