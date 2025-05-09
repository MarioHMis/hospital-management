package com.hospital.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "doctors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio.")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "El apellido paterno es obligatorio.")
    private String lastName;

    @Column(nullable = true)
    private String middleName;

    @Column(nullable = false)
    @NotBlank(message = "La especialidad es obligatoria.")
    private String specialty;
}
