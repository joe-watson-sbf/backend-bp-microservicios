package com.joedev.accountservices.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReporteEstadoCuenta {
    private String cliente;
    private List<Detalle> detalles;
}
