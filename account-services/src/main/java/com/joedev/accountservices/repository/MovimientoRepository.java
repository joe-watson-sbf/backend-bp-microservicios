package com.joedev.accountservices.repository;

import com.joedev.accountservices.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findAllByCuenta_ClienteIdAndFechaBetween(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin);
}
