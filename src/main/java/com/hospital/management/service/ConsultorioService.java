package com.hospital.management.service;

import com.hospital.management.model.Consultorio;
import com.hospital.management.repository.ConsultorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConsultorioService {
    private final ConsultorioRepository consultorioRepository;

    public List<Consultorio> getAllConsultorios() {
        return consultorioRepository.findAll();
    }

    public Consultorio createConsultorio(Consultorio consultorio) {
        return consultorioRepository.save(consultorio);
    }

    public Optional<Consultorio> getConsultorioById(Long id) {
        return consultorioRepository.findById(id);
    }

    public void deleteConsultorio(Long id) {
        consultorioRepository.deleteById(id);
    }
}
