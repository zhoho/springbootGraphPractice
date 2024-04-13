package com.example.springGraph.service;

import com.example.springGraph.entity.Location;
import com.example.springGraph.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Optional<Location> findLocationByCityDistrictSubdistrict(String city, String district, String subdistrict) {
        return locationRepository.findByCityAndDistrictAndSubdistrict(city, district, subdistrict);
    }
    public List<String> findCities(String searchTerm) {
        return locationRepository.findByCityContaining(searchTerm)
                .stream()
                .map(Location::getCity)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> findDistricts(String searchTerm) {
        return locationRepository.findByDistrictContaining(searchTerm)
                .stream()
                .map(Location::getDistrict)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> findSubdistricts(String searchTerm) {
        return locationRepository.findBySubdistrictContaining(searchTerm)
                .stream()
                .map(Location::getSubdistrict)
                .distinct()
                .collect(Collectors.toList());
    }


}
