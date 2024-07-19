package com.joedev.accountservices.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joedev.accountservices.entity.TipoCuenta;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
    private Long clienteId;
    @JsonIgnoreProperties("cuenta")
    private List<MovimientoDto> movimientos;
}