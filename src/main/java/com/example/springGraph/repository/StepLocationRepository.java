package com.example.springGraph.repository;

import com.example.springGraph.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StepLocationRepository extends JpaRepository<Location, Long> {

    @Query("SELECT DISTINCT l.city FROM Location l")
    List<String> findDistinctStep1();

    @Query("SELECT l.district FROM Location l WHERE l.city = :step1")
    List<String> findStep2ByStep1(@Param("step1") String step1);

    @Query("SELECT l.subdistrict FROM Location l WHERE l.district = :step2")
    List<String> findStep3ByStep2(@Param("step2") String step2);

    @Query("SELECT l FROM Location l WHERE l.city = :city AND l.district = :district AND l.subdistrict = :subdistrict")
    Optional<Location> findCoordinatesByLocation(@Param("city") String city, @Param("district") String district, @Param("subdistrict") String subdistrict);

}
