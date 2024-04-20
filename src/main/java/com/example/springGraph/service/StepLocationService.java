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

    public List<String> getCities() {
        return locationRepository.findDistinctCities();
    }

    public List<String> getDistrictsByCity(String city) {
        return locationRepository.findDistrictsByCity(city);
    }

    public Location getLocationCoordinates(String city, String district, String subdistrict) {
        return locationRepository.findByCityDistrictSubdistrict(city, district, subdistrict).orElse(null);
    }

    public List<String> getSubdistrictsByCityAndDistrict(String city, String district) {
        return locationRepository.findSubdistrictsByCityAndDistrict(city, district);
    }
}
