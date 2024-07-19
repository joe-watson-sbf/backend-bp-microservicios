package com.joedev.clientservicios.dto;

import lombok.*;

/**
 * DTO for {@link com.joedev.clientservicios.entity.Cliente}
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto extends PersonaDto {
    private String contrasena;
    private Boolean estado;

    public String getContrasena(){
        return "*****************";
    }
}