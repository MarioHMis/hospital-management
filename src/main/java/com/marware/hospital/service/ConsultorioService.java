package com.marware.hospital.service;

import com.marware.hospital.model.Consultorio;
import com.marware.hospital.repository.ConsultorioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
