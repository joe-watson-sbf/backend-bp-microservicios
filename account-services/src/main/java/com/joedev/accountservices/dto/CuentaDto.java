package com.joedev.accountservices.dto;

import com.joedev.accountservices.entity.TipoCuenta;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.joedev.accountservices.entity.Cuenta}
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuentaDto {
    private Long id;
    private String numero;
    private TipoCuenta tipo;
    private BigDecimal saldoInicial;
    private Boolean estado;
}