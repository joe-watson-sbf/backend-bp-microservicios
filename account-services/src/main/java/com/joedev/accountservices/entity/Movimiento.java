package com.joedev.accountservices.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "movimientos")
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    @Enumerated(EnumType.STRING)
    private TipoDeMovimiento tipo;
    private BigDecimal valor;
    private BigDecimal saldo;
    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    @JsonBackReference
    private Cuenta cuenta;

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
