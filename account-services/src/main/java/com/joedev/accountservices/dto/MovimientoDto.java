package com.joedev.accountservices.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joedev.accountservices.entity.TipoDeMovimiento;
import lombok.*;
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
    private TipoDeMovimiento tipo;
    private BigDecimal valor;
    private BigDecimal saldo;
    private BigDecimal saldoAnterior;
    @JsonIgnoreProperties("movimientos")
    private CuentaDto cuenta;
}