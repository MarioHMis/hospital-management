package com.kosmos.hospital.controller;

import com.kosmos.hospital.model.Consultorio;
import com.kosmos.hospital.service.ConsultorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultorios")
public class ConsultorioController {
    private final ConsultorioService consultorioService;

    @Autowired
    public ConsultorioController(ConsultorioService consultorioService) {
        this.consultorioService = consultorioService;
    }

    @GetMapping
    public ResponseEntity<List<Consultorio>> getAllConsultorios() {
        List<Consultorio> consultorios = consultorioService.getAllConsultorios();
        return ResponseEntity.ok(consultorios);
    }

    @PostMapping
    public ResponseEntity<Consultorio> createConsultorio(@RequestBody Consultorio consultorio) {
        Consultorio newConsultorio = consultorioService.createConsultorio(consultorio);
        return ResponseEntity.ok(newConsultorio);
    }
}
