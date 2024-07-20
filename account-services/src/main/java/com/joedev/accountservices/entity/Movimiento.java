package com.joedev.accountservices.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movimiento_seq")
    @SequenceGenerator(name = "movimiento_seq", sequenceName = "movimiento_sequence", allocationSize = 1)
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
    private BigDecimal saldoAnterior;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDate.now();
    }
}
