package com.joedev.accountservices.services;

import com.joedev.accountservices.dto.ReportePorFechas;
import com.joedev.accountservices.entity.Movimiento;
import com.joedev.accountservices.repository.MovimientoRepository;
import com.joedev.accountservices.services.cliente.ClienteModel;
import com.joedev.accountservices.services.cliente.ClienteApi;
import exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import static utils.Utils.requireNonNull;

@RequiredArgsConstructor
@Service
public class ReporteServicio {

    private final MovimientoRepository movimientoRepository;
    private final ClienteApi clienteApi;

    public List<ReportePorFechas> generarReporteEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {
        requireNonNull(clienteId, "El id del cliente no puede ser nulo");
        requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula");
        requireNonNull(fechaFin, "La fecha de fin no puede ser nula");

        ClienteModel cliente = clienteApi.findById(clienteId)
                .orElseThrow(() -> new BusinessException("Se ocurrió un error, el cliente con el id: " + clienteId + " no existe"));

       List<Movimiento> movimientos = movimientoRepository.findAllByCuenta_ClienteIdAndFechaBetween(clienteId, fechaInicio, fechaFin);

        return movimientos.stream().map(movimiento -> ReportePorFechas.builder()
                    .cliente(cliente.getNombre())
                    .estado(movimiento.getCuenta().getEstado())
                    .fecha(movimiento.getFecha())
                    .tipo(movimiento.getCuenta().getTipo())
                    .numeroCuenta(movimiento.getCuenta().getNumero())
                    .saldoInicial(movimiento.getSaldoAnterior())
                    .saldoDisponible(movimiento.getSaldo())
                    .movimiento(movimiento.getValor())
                    .build()
        ).toList();

    }
}
