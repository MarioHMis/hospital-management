package com.marware.hospital.controller;

import com.marware.hospital.model.Doctor;
import com.marware.hospital.response.ApiResponse;
import com.marware.hospital.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Doctor>>> getAllDoctors() {
        return ResponseEntity.ok(
                ApiResponse.<List<Doctor>>builder()
                        .timestamp(LocalDateTime.now())
                        .status(200)
                        .message("Doctors retrieved successfully")
                        .data(doctorService.getAllDoctors())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Doctor>> createDoctor(@RequestBody Doctor doctor) {
        return ResponseEntity.ok(
                ApiResponse.<Doctor>builder()
                        .timestamp(LocalDateTime.now())
                        .status(200)
                        .message("Doctor created successfully")
                        .data(doctorService.createDoctor(doctor))
                        .build()
        );
    }
}
