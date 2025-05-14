package com.marware.hospital.repository;

import com.marware.hospital.model.Cita;
import com.marware.hospital.model.Doctor;
import com.marware.hospital.model.Consultorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {

    List<Cita> findByDoctorAndAppointmentDateTimeBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);

    List<Cita> findByConsultorioAndAppointmentDateTimeBetween(Consultorio consultorio, LocalDateTime start, LocalDateTime end);

    List<Cita> findByPatientNameAndAppointmentDateTimeBetween(String patientName, LocalDateTime start, LocalDateTime end);

    boolean existsByConsultorioAndAppointmentDateTime(Consultorio consultorio, LocalDateTime appointmentDateTime);

    boolean existsByDoctorAndAppointmentDateTime(Doctor doctor, LocalDateTime appointmentDateTime);
}
