package com.joedev.clientservicios.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private Integer edad;
    @Column(unique = true)
    private String identificacion;
    private String direccion;
    private String telefono;
}
