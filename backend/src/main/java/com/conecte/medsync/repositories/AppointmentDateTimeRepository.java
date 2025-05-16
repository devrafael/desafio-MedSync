package com.conecte.medsync.repositories;

import com.conecte.medsync.models.AppointmentDateTimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentDateTimeRepository extends JpaRepository<AppointmentDateTimeModel, UUID> {


    @Query("""
    SELECT a 
    FROM AppointmentDateTimeModel a 
    WHERE a.date = :date 
      AND a.time = :time 
      AND LOWER(a.doctor) = LOWER(:doctor)
""")
    Optional<AppointmentDateTimeModel> findByDateTimeAndDoctor(
            @Param("date") LocalDate date,
            @Param("time") LocalTime time,
            @Param("doctor") String doctor
    );

    @Query("""
    SELECT a 
    FROM AppointmentDateTimeModel a 
    WHERE TRIM(LOWER(a.doctor)) = TRIM(LOWER(:doctor))
    ORDER BY a.date ASC

""")
    List<AppointmentDateTimeModel> findDateTimeByDoctor(@Param("doctor") String doctor);
}
