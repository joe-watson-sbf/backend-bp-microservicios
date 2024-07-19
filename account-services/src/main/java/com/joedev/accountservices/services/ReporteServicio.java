package com.joedev.accountservices.services;

import com.joedev.accountservices.dto.Detalle;
import com.joedev.accountservices.dto.MovimientoDto;
import com.joedev.accountservices.dto.ReporteEstadoCuenta;
import com.joedev.accountservices.entity.Cuenta;
import com.joedev.accountservices.repository.CuentaRepository;
import com.joedev.accountservices.services.cliente.ClienteModel;
import com.joedev.accountservices.services.cliente.ClienteApi;
import exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReporteServicio {

    private final CuentaRepository cuentaRepository;
    private final ClienteApi clienteApi;
    public ReporteEstadoCuenta generarReporteEstadoCuenta(Long clienteId, LocalDate fechaInicio, LocalDate fechaFin) {


        ClienteModel cliente = clienteApi.findById(clienteId)
                .orElseThrow(() -> new BusinessException("Se ocurrió un error al obtener el cliente!"));

        List<Cuenta> cuentas = cuentaRepository.findCuentaByClienteId(clienteId);

        List<Detalle> cuentaDetalles = new ArrayList<>();

        return new ReporteEstadoCuenta(cliente.getNombre(), cuentaDetalles);
    }
}
