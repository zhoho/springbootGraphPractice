package com.example.springGraph.repository;

import com.example.springGraph.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StepLocationRepository extends JpaRepository<Location, Long> {


    @Query("SELECT DISTINCT l.city FROM Location l")
    List<String> findDistinctCities();

    @Query("SELECT DISTINCT l.district FROM Location l WHERE l.city = :city")
    List<String> findDistrictsByCity(@Param("city") String city);

    // city와 district에 따른 subdistrict 목록을 가져오는 쿼리
    @Query("SELECT DISTINCT l.subdistrict FROM Location l WHERE l.city = :city AND l.district = :district")
    List<String> findSubdistrictsByCityAndDistrict(@Param("city") String city, @Param("district") String district);

    @Query("SELECT l FROM Location l WHERE l.city = :city AND l.district = :district AND l.subdistrict = :subdistrict")
    Optional<Location> findByCityDistrictSubdistrict(@Param("city") String city, @Param("district") String district, @Param("subdistrict") String subdistrict);

}
