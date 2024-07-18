package com.joedev.accountservices.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@Entity(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private TipoDeMovimiento tipo;
    private BigDecimal valor;
    private BigDecimal saldo;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDate.now();
    }

    private void manejoTipoDeMovimiento(TipoDeMovimiento tipo) {
        if (tipo == TipoDeMovimiento.RETIRO) {
            this.saldo = this.saldo.subtract(this.valor);
        } else if (tipo == TipoDeMovimiento.DEPOSITO) {
            this.saldo = this.saldo.add(this.valor);
        }
    }
}
