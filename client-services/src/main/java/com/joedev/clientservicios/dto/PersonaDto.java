package com.joedev.clientservicios.dto;

import com.joedev.clientservicios.entity.Genero;
import lombok.*;

/**
 * DTO for {@link com.joedev.clientservicios.entity.Persona}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonaDto {
    private Long id;
    private String nombre;
    private Genero genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
}