package com.marware.hospital.controller;

import com.marware.hospital.model.Consultorio;
import com.marware.hospital.response.ApiResponse;
import com.marware.hospital.service.ConsultorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/consultorios")
@RequiredArgsConstructor
public class ConsultorioController {

    private final ConsultorioService consultorioService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Consultorio>>> getAllConsultorios() {
        return ResponseEntity.ok(
                ApiResponse.<List<Consultorio>>builder()
                        .timestamp(LocalDateTime.now())
                        .status(200)
                        .message("Consultorios retrieved successfully")
                        .data(consultorioService.getAllConsultorios())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Consultorio>> createConsultorio(@RequestBody Consultorio consultorio) {
        return ResponseEntity.ok(
                ApiResponse.<Consultorio>builder()
                        .timestamp(LocalDateTime.now())
                        .status(200)
                        .message("Consultorio created successfully")
                        .data(consultorioService.createConsultorio(consultorio))
                        .build()
        );
    }
}
