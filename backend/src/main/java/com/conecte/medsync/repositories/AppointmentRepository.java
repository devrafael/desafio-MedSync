package com.conecte.medsync.repositories;

import com.conecte.medsync.models.AppointmentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentModel, UUID> {

    @Query(
            """
            SELECT a
            FROM AppointmentModel a
            WHERE a.appointmentCompleted = :completed
            AND TRIM(LOWER(a.patient)) = TRIM(LOWER(:patient))
            """
    )
    List<AppointmentModel> findByCompletedAndPatient(
            @Param("completed") boolean completed,
            @Param("patient") String patient);
//
//    @Query(
//            """
//            SELECT a
//            FROM AppointmentModel a
//            WHERE TRIM(LOWER(a.appointmentDateTime.doctor)) = TRIM(LOWER(:doctor))
//            """
//    )
@Query(
        """
        SELECT a
        FROM AppointmentModel a
        WHERE TRIM(LOWER(a.appointmentDateTime.doctor)) = TRIM(LOWER(:doctor))
        ORDER BY a.appointmentDateTime.date ASC, a.appointmentDateTime.time ASC
        """
)
    List<AppointmentModel> findByAppointmentsByDoctor(@Param("doctor") String doctor);

}
