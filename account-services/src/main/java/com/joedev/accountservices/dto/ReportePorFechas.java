package com.joedev.accountservices.dto;

import com.joedev.accountservices.entity.TipoCuenta;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReportePorFechas {
    private LocalDate fecha;
    private String cliente;
    private Long numeroCuenta;
    private TipoCuenta tipo;
    private BigDecimal saldoInicial;
    private BigDecimal saldoDisponible;
    private boolean estado;
    private BigDecimal movimiento;
}
