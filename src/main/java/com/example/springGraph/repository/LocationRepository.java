package com.example.springGraph.repository;

import com.example.springGraph.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findByCityAndDistrictAndSubdistrict(String city, String district, String subdistrict);

    List<Location> findByCityContaining(String city);

    List<Location> findByDistrictContaining(String district);

    List<Location> findBySubdistrictContaining(String subdistrict);
}
