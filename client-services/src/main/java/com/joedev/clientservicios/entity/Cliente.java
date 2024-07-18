package com.joedev.clientservicios.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "clientes")
public class Cliente extends Persona {
    private Long id;
    private String contrasena;
    private Boolean estado;

    @PrePersist
    public void prePersist() {
        this.estado = true;
    }
}

