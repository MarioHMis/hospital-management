package com.kosmos.hospital.repository;

import com.kosmos.hospital.model.Cita;
import com.kosmos.hospital.model.Consultorio;
import com.kosmos.hospital.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    boolean existsByConsultorioAndAppointmentTime(Consultorio consultorio, LocalDateTime appointmentTime);

    boolean existsByDoctorAndAppointmentTime(Doctor doctor, LocalDateTime appointmentTime);

    List<Cita> findByAppointmentTimeBetween(LocalDateTime start, LocalDateTime end);

    List<Cita> findByConsultorioAndAppointmentTimeBetween(Consultorio consultorio, LocalDateTime start, LocalDateTime end);

    List<Cita> findByDoctorAndAppointmentTimeBetween(Doctor doctor, LocalDateTime start, LocalDateTime end);
}
