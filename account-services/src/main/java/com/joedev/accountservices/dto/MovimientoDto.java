package com.joedev.accountservices.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO for {@link com.joedev.accountservices.entity.Movimiento}
 */
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoDto {
    private Long id;
    private LocalDate fecha;
    private String tipo;
    private BigDecimal valor;
    private BigDecimal saldo;
}