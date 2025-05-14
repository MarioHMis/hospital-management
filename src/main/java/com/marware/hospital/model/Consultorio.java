package com.marware.hospital.model;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String floor;
}
