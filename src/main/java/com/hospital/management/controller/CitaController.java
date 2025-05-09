package com.hospital.management.controller;

import com.hospital.management.model.Cita;
import com.hospital.management.service.CitaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@RequiredArgsConstructor
public class CitaController {
    private final CitaService citaService;

    @GetMapping
    public ResponseEntity<List<Cita>> getAllCitas() {
        return ResponseEntity.ok(citaService.getAllCitas());
    }

    @PostMapping
    public ResponseEntity<Cita> createCita(@RequestBody Cita cita) {
        return ResponseEntity.status(HttpStatus.CREATED).body(citaService.createCita(cita));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cita> getCitaById(@PathVariable Long id) {
        return citaService.getCitaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCita(@PathVariable Long id) {
        citaService.deleteCita(id);
        return ResponseEntity.noContent().build();
    }
}
