package com.hospital.management.repository;

import com.hospital.management.model.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long> {
    List<Cita> findByDoctorId(Long doctorId);
    List<Cita> findByConsultorioId(Long consultorioId);
    List<Cita> findByPacienteId(Long pacienteId);
    List<Cita> findByAppointmentTimeBetween(LocalDateTime start, LocalDateTime end);
}
