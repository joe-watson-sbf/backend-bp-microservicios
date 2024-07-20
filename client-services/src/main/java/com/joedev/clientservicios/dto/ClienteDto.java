package com.joedev.clientservicios.dto;

import com.joedev.clientservicios.entity.Genero;
import lombok.*;

/**
 * DTO for {@link com.joedev.clientservicios.entity.Cliente}
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDto {
    private Long id;
    private String nombre;
    private Genero genero;
    private Integer edad;
    private String identificacion;
    private String direccion;
    private String telefono;
    // se puede quitar el atributo contrasena usando la anotación @JsonIgnoreProperties
    private String contrasena;
    private Boolean estado;

    public String getContrasena(){
        return "*****************";
    }
}