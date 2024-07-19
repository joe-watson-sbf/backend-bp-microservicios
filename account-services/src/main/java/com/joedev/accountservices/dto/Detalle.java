package com.joedev.accountservices.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Detalle {
    private Integer numeroCuenta;
    private BigDecimal saldo;
    private List<MovimientoDto> movimientos;
}
