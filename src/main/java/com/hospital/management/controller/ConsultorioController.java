package com.hospital.management.controller;

import com.hospital.management.model.Consultorio;
import com.hospital.management.service.ConsultorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultorios")
@RequiredArgsConstructor
public class ConsultorioController {
    private final ConsultorioService consultorioService;

    @GetMapping
    public ResponseEntity<List<Consultorio>> getAllConsultorios() {
        return ResponseEntity.ok(consultorioService.getAllConsultorios());
    }

    @PostMapping
    public ResponseEntity<Consultorio> createConsultorio(@RequestBody Consultorio consultorio) {
        return ResponseEntity.status(HttpStatus.CREATED).body(consultorioService.createConsultorio(consultorio));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultorio> getConsultorioById(@PathVariable Long id) {
        return consultorioService.getConsultorioById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultorio(@PathVariable Long id) {
        consultorioService.deleteConsultorio(id);
        return ResponseEntity.noContent().build();
    }
}
