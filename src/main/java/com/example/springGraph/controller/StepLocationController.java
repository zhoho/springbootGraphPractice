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

import java.util.List;

@RestController
public class StepLocationController {

    @Autowired
    private StepLocationService locationService;

    @GetMapping("/api/step1")
    public ResponseEntity<List<String>> getStep1() {
        List<String> city = locationService.getDistinctStep1();
        return ResponseEntity.ok(city);
    }

    @GetMapping("/api/step2/{step1}")
    public ResponseEntity<List<String>> getStep2(@PathVariable String step1) {
        List<String> district = locationService.getStep2ForStep1(step1);
        return ResponseEntity.ok(district);
    }

    @GetMapping("/api/step3/{step2}")
    public ResponseEntity<List<String>> getStep3(@PathVariable String step2) {
        List<String> subdistrict = locationService.getStep3ForStep2(step2);
        return ResponseEntity.ok(subdistrict);
    }

    @GetMapping("/coordinates")
    public ResponseEntity<Location> getCoordinates(@RequestParam String city, @RequestParam String district, @RequestParam String subdistrict) {
        Location location = locationService.getCoordinates(city, district, subdistrict);
        return location != null ? ResponseEntity.ok(location) : ResponseEntity.notFound().build();
    }
}
