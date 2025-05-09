package com.hospital.management.controller;

import com.hospital.management.model.Paciente;
import com.hospital.management.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@RequiredArgsConstructor
public class PacienteController {
    private final PacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        return ResponseEntity.ok(pacienteService.getAllPacientes());
    }

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.createPaciente(paciente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        return pacienteService.getPacienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        pacienteService.deletePaciente(id);
        return ResponseEntity.noContent().build();
    }
}
