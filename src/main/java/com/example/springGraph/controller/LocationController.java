package com.example.springGraph.controller;

import com.example.springGraph.entity.Location;
import com.example.springGraph.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // 타임리프 템플릿 이름 (index.html)
    }

    @PostMapping("/getCoordinates")
    public String getCoordinates(@RequestParam("city") String city,
                                 @RequestParam("district") String district,
                                 @RequestParam("subdistrict") String subdistrict,
                                 Model model) {
        Optional<Location> locationOptional = locationService.findLocationByCityDistrictSubdistrict(city, district, subdistrict);

        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            model.addAttribute("gridX", location.getX());
            model.addAttribute("gridY", location.getY());
        } else {
            model.addAttribute("message", "No matching data found.");
        }

        return "index";
    }
    @GetMapping("/searchAddress")
    @ResponseBody
    public List<String> searchAddress(@RequestParam("type") String type, @RequestParam("searchTerm") String searchTerm) {
        switch (type) {
            case "city":
                return locationService.findCities(searchTerm);
            case "district":
                return locationService.findDistricts(searchTerm);
            case "subdistrict":
                return locationService.findSubdistricts(searchTerm);
            default:
                return new ArrayList<>();
        }
    }

}
