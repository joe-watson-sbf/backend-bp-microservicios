package com.joedev.accountservices.dto;

import com.joedev.accountservices.entity.TipoCuenta;
import com.joedev.accountservices.entity.TipoDeMovimiento;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * {
 *
 * "Fecha":"10/2/2022",
 *
 * "Cliente":"Marianela Montalvo",
 *
 * "Numero Cuenta":"225487"
 *
 * "Tipo":"Corriente",
 *
 * "Saldo Inicial":100,
 *
 * "Estado":true,
 *
 * "Movimiento":600,
 *
 * "Saldo Disponible":700
 *
 * }
 * */

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
