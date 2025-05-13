package com.kosmos.hospital.service;

import com.kosmos.hospital.model.Cita;
import com.kosmos.hospital.model.Consultorio;
import com.kosmos.hospital.model.Doctor;
import com.kosmos.hospital.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
    private final CitaRepository citaRepository;

    @Autowired
    public CitaService(CitaRepository citaRepository) {
        this.citaRepository = citaRepository;
    }

    @Transactional
    public Cita createCita(Cita cita) {
        validateAppointmentRules(cita);
        return citaRepository.save(cita);
    }

    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> getCitaById(Long id) {
        return citaRepository.findById(id);
    }

    @Transactional
    public void deleteCita(Long id) {
        citaRepository.deleteById(id);
    }

    @Transactional
    public Cita updateCita(Long id, Cita updatedCita) {
        Optional<Cita> existingCitaOpt = citaRepository.findById(id);

        if (existingCitaOpt.isEmpty()) {
            throw new IllegalArgumentException("The specified appointment does not exist.");
        }

        Cita existingCita = existingCitaOpt.get();
        existingCita.setDoctor(updatedCita.getDoctor());
        existingCita.setConsultorio(updatedCita.getConsultorio());
        existingCita.setAppointmentTime(updatedCita.getAppointmentTime());
        existingCita.setPatientName(updatedCita.getPatientName());

        validateAppointmentRules(existingCita);
        return citaRepository.save(existingCita);
    }

    private void validateAppointmentRules(Cita cita) {
        // Regla 1: No se puede agendar cita en el mismo consultorio a la misma hora.
        if (citaRepository.existsByConsultorioAndAppointmentTime(cita.getConsultorio(), cita.getAppointmentTime())) {
            throw new IllegalArgumentException("The consultorio is already booked at this time.");
        }

        // Regla 2: No se puede agendar cita para un mismo doctor a la misma hora.
        if (citaRepository.existsByDoctorAndAppointmentTime(cita.getDoctor(), cita.getAppointmentTime())) {
            throw new IllegalArgumentException("The doctor is already booked at this time.");
        }

        // Regla 3: Verificar que el paciente no tenga otra cita en el mismo día con menos de 2 horas de diferencia.
        LocalDateTime startOfDay = cita.getAppointmentTime().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = cita.getAppointmentTime().toLocalDate().atTime(LocalTime.MAX);
        List<Cita> citasDelDia = citaRepository.findByAppointmentTimeBetween(startOfDay, endOfDay);

        for (Cita existingCita : citasDelDia) {
            if (existingCita.getId().equals(cita.getId())) continue;

            if (existingCita.getPatientName().equalsIgnoreCase(cita.getPatientName())) {
                if (Math.abs(existingCita.getAppointmentTime().getHour() - cita.getAppointmentTime().getHour()) < 2) {
                    throw new IllegalArgumentException("The patient cannot have another appointment within 2 hours of an existing one.");
                }
            }
        }

        // Regla 4: Un doctor no puede tener más de 8 citas en el mismo día.
        List<Cita> citasDoctor = citaRepository.findByDoctorAndAppointmentTimeBetween(
                cita.getDoctor(), startOfDay, endOfDay);

        if (citasDoctor.size() >= 8) {
            throw new IllegalArgumentException("The doctor cannot have more than 8 appointments in a single day.");
        }
    }
}
