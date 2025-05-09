package com.hospital.management.service;

import com.hospital.management.model.Paciente;
import com.hospital.management.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PacienteService {
    private final PacienteRepository pacienteRepository;

    public List<Paciente> getAllPacientes() {
        return pacienteRepository.findAll();
    }

    public Paciente createPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public Optional<Paciente> getPacienteById(Long id) {
        return pacienteRepository.findById(id);
    }

    public void deletePaciente(Long id) {
        pacienteRepository.deleteById(id);
    }
}
