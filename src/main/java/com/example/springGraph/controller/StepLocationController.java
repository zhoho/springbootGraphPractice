package com.example.springGraph.controller;

import com.example.springGraph.entity.Location;
import com.example.springGraph.repository.StepLocationRepository;
import com.example.springGraph.service.LocationService;
import com.example.springGraph.service.StepLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StepLocationController {

    @Autowired
    private StepLocationService locationService;

    @GetMapping("/api/step1")
    public ResponseEntity<List<String>> getStep1() {
        List<String> city = locationService.getDistinctStep1();
        return ResponseEntity.ok(city);
    }

    @GetMapping("/api/districts/{city}")
    public ResponseEntity<List<String>> getDistrictsByCity(@PathVariable String city) {
        List<String> districts = locationService.getDistrictsByCity(city);
        return districts != null && !districts.isEmpty() ? ResponseEntity.ok(districts) : ResponseEntity.notFound().build();
    }

    // city와 district에 따른 subdistrict 목록을 반환하는 엔드포인트
    @GetMapping("/api/subdistricts/{city}/{district}")
    public ResponseEntity<List<String>> getSubdistrictsByCityAndDistrict(@PathVariable String city, @PathVariable String district) {
        List<String> subdistricts = locationService.getSubdistrictsByCityAndDistrict(city, district);
        return subdistricts != null && !subdistricts.isEmpty() ? ResponseEntity.ok(subdistricts) : ResponseEntity.notFound().build();
    }

    @GetMapping("/api/coordinates")
    public ResponseEntity<?> getCoordinates(@RequestParam String city, @RequestParam String district, @RequestParam String subdistrict) {
        Location location = locationService.getLocationCoordinates(city, district, subdistrict);
        if (location != null) {
            // 좌표 객체를 만들어 반환합니다.
            Map<String, String> coordinates = new HashMap<>();
            coordinates.put("X", location.getX());
            coordinates.put("Y", location.getY());
            return ResponseEntity.ok(coordinates);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
