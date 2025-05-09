package com.hospital.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "pacientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre es obligatorio.")
    private String firstName;

    @Column(nullable = false)
    @NotBlank(message = "El apellido paterno es obligatorio.")
    private String lastName;

    @Column(nullable = false)
    @NotBlank(message = "El apellido materno es obligatorio.")
    private String middleName;

}
