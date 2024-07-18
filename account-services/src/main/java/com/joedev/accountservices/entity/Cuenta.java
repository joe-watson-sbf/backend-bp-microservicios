package com.joedev.accountservices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cuentas")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @GeneratedValue(generator = "account-number-generator")
    @GenericGenerator(
            name = "account-number-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "account_number_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "834321"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "7")
            }
    )
    @Column(unique = true)
    private Integer numero;
    private TipoCuenta tipo;
    private BigDecimal saldoInicial;
    private Boolean estado;

    @PrePersist
    public void prePersist() {
        this.estado = true;
    }
}