package com.marware.hospital.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "consultorio_id", nullable = false)
    private com.marware.hospital.model.Consultorio consultorio;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private com.marware.hospital.model.Doctor doctor;

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    @Column(nullable = false)
    private String patientName;
}
