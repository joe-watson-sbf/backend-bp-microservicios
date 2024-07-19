package com.joedev.accountservices.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import exceptions.BusinessException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer numero;
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipo;
    private BigDecimal saldoInicial;
    private Boolean estado;
    @Column(nullable = false)
    private Long clienteId;
    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Movimiento> movimientos;

    @PrePersist
    public void prePersist() {
        this.estado = true;
        this.generarNumeroDeCuenta();
        this.movimientos = new ArrayList<>();
        if (this.saldoInicial == null) {
            this.saldoInicial = BigDecimal.ZERO;
        }
        if (this.tipo == null) {
            this.tipo = TipoCuenta.AHORROS;
        }
    }

    private void generarNumeroDeCuenta() {
        this.numero = (int) (Math.random() * 1000000);
    }

    public void actualizarSaldo(BigDecimal valor) {
        this.saldoInicial = this.saldoInicial.add(valor);
    }

    public void asegurarQueTieneFondoDisponible(BigDecimal valorIngresado){
        if (valorIngresado.compareTo(BigDecimal.ZERO) < 0 && this.getSaldoInicial().compareTo(valorIngresado.abs()) < 0) {
            throw new BusinessException("Saldo no disponible");
        }
    }

    public void agregarMovimiento(Movimiento movimiento) {
        movimientos.add(movimiento);
        movimiento.setCuenta(this);
        this.actualizarSaldo(movimiento.getValor());
    }

}