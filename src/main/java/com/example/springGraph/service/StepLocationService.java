package com.example.springGraph.service;

import com.example.springGraph.entity.Location;
import com.example.springGraph.repository.StepLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StepLocationService {

    @Autowired
    private StepLocationRepository locationRepository;

    public List<String> getDistinctStep1() {
        // 데이터베이스에서 1단계 데이터 조회
        return locationRepository.findDistinctStep1();
    }

    public List<String> getStep2ForStep1(String step1) {
        // 데이터베이스에서 step1에 해당하는 2단계 데이터 조회
        return locationRepository.findStep2ByStep1(step1);
    }

    public List<String> getStep3ForStep2(String step2) {
        // 데이터베이스에서 step2에 해당하는 3단계 데이터 조회
        return locationRepository.findStep3ByStep2(step2);
    }
    public Location getCoordinates(String city, String district, String subdistrict) {
        return locationRepository.findCoordinatesByLocation(city, district, subdistrict).orElse(null);
    }
}
