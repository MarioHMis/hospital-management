package com.hospital.management.service;

import com.hospital.management.exception.ResourceNotFoundException;
import com.hospital.management.model.Cita;
import com.hospital.management.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CitaService {
    private final CitaRepository citaRepository;

    @Transactional
    public Cita createCita(Cita cita) {
        validarCita(cita);
        return citaRepository.save(cita);
    }

    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    public Optional<Cita> getCitaById(Long id) {
        return Optional.ofNullable(citaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con ID: " + id)));
    }


    public void deleteCita(Long id) {
        if (!citaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cita no encontrada con ID: " + id);
        }
        citaRepository.deleteById(id);
    }


    private void validarCita(Cita cita) {

        if (!citaRepository.findByConsultorioId(cita.getConsultorio().getId()).stream()
                .noneMatch(existingCita -> existingCita.getAppointmentTime().equals(cita.getAppointmentTime()))) {
            throw new IllegalArgumentException("No se puede agendar cita en el mismo consultorio a la misma hora.");
        }


        if (!citaRepository.findByDoctorId(cita.getDoctor().getId()).stream()
                .noneMatch(existingCita -> existingCita.getAppointmentTime().equals(cita.getAppointmentTime()))) {
            throw new IllegalArgumentException("No se puede agendar cita para el mismo doctor a la misma hora.");
        }


        if (!citaRepository.findByPacienteId(cita.getPaciente().getId()).stream()
                .noneMatch(existingCita -> existingCita.getAppointmentTime().isBefore(cita.getAppointmentTime().plusHours(2))
                        && existingCita.getAppointmentTime().isAfter(cita.getAppointmentTime().minusHours(2)))) {
            throw new IllegalArgumentException("El paciente no puede tener una cita con menos de 2 horas de diferencia.");
        }


        long citasDelDia = citaRepository.findByDoctorId(cita.getDoctor().getId()).stream()
                .filter(existingCita -> existingCita.getAppointmentTime().toLocalDate().equals(cita.getAppointmentTime().toLocalDate()))
                .count();

        if (citasDelDia >= 8) {
            throw new IllegalArgumentException("El doctor ya tiene el límite de 8 citas para el día.");
        }
    }
}
