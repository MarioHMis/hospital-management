package com.marware.hospital.service;

import com.marware.hospital.model.Cita;
import com.marware.hospital.model.Doctor;
import com.marware.hospital.model.Consultorio;
import com.marware.hospital.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    private final CitaRepository citaRepository;

    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    @Transactional
    public Cita createCita(Cita cita) {
        validateCita(cita);
        return citaRepository.save(cita);
    }

    @Transactional
    public void cancelCita(Long citaId) {
        citaRepository.deleteById(citaId);
    }

    @Transactional
    public Cita updateCita(Long citaId, Cita updatedCita) {
        Cita existingCita = citaRepository.findById(citaId)
                .orElseThrow(() -> new IllegalArgumentException("Cita not found"));

        validateCita(updatedCita);
        existingCita.setConsultorio(updatedCita.getConsultorio());
        existingCita.setDoctor(updatedCita.getDoctor());
        existingCita.setAppointmentDateTime(updatedCita.getAppointmentDateTime());
        existingCita.setPatientName(updatedCita.getPatientName());

        return citaRepository.save(existingCita);
    }

    private void validateCita(Cita cita) {
        if (citaRepository.existsByConsultorioAndAppointmentDateTime(cita.getConsultorio(), cita.getAppointmentDateTime())) {
            throw new IllegalArgumentException("Appointment conflict: The selected consultorio is not available at this time.");
        }

        if (citaRepository.existsByDoctorAndAppointmentDateTime(cita.getDoctor(), cita.getAppointmentDateTime())) {
            throw new IllegalArgumentException("Appointment conflict: The selected doctor is not available at this time.");
        }

        validateDoctorAppointmentLimit(cita.getDoctor(), cita.getAppointmentDateTime());
        validatePatientAppointmentRestriction(cita.getPatientName(), cita.getAppointmentDateTime());
    }

    private void validateDoctorAppointmentLimit(Doctor doctor, LocalDateTime appointmentDateTime) {
        LocalDate date = appointmentDateTime.toLocalDate();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        long doctorAppointments = citaRepository.findByDoctorAndAppointmentDateTimeBetween(
                doctor, startOfDay, endOfDay).size();

        if (doctorAppointments >= 8) {
            throw new IllegalArgumentException("Doctor limit exceeded: The doctor has reached the maximum number of 8 appointments for the day.");
        }
    }

    private void validatePatientAppointmentRestriction(String patientName, LocalDateTime appointmentDateTime) {
        LocalDate date = appointmentDateTime.toLocalDate();
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(LocalTime.MAX);

        List<Cita> patientAppointments = citaRepository.findByPatientNameAndAppointmentDateTimeBetween(
                patientName, startOfDay, endOfDay);

        for (Cita cita : patientAppointments) {
            if (Math.abs(cita.getAppointmentDateTime().getHour() - appointmentDateTime.getHour()) < 2) {
                throw new IllegalArgumentException("Patient restriction: The patient cannot have another appointment within 2 hours.");
            }
        }
    }

}
