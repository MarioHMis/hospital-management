package com.hospital.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Table(name = "consultorios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Consultorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull(message = "El número de consultorio es obligatorio.")
    private Integer roomNumber;

    @Column(nullable = false)
    @NotNull(message = "El piso es obligatorio.")
    private Integer floor;

}
