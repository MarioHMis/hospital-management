package com.kosmos.hospital.service;

import com.kosmos.hospital.model.Consultorio;
import com.kosmos.hospital.repository.ConsultorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultorioService {
    private final ConsultorioRepository consultorioRepository;

    @Autowired
    public ConsultorioService(ConsultorioRepository consultorioRepository) {
        this.consultorioRepository = consultorioRepository;
    }

    public List<Consultorio> getAllConsultorios() {
        return consultorioRepository.findAll();
    }

    public Consultorio createConsultorio(Consultorio consultorio) {
        return consultorioRepository.save(consultorio);
    }
}
