package com.hospital.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

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
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull(message = "El doctor es obligatorio.")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "consultorio_id", nullable = false)
    @NotNull(message = "El consultorio es obligatorio.")
    private Consultorio consultorio;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    @NotNull(message = "El paciente es obligatorio.")
    private Paciente paciente;

    @Column(nullable = false)
    @NotNull(message = "La fecha y hora de la cita son obligatorias.")
    private LocalDateTime appointmentTime;

}
