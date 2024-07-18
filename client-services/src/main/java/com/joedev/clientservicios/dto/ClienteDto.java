package com.joedev.clientservicios.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joedev.clientservicios.entity.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link com.joedev.clientservicios.entity.Cliente}
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// This annotation is used to ignore the password field when serializing the object to JSON
@JsonIgnoreProperties({"contrasena"})
public class ClienteDto extends PersonaDto {
    private String contrasena;
    private Boolean estado;
}