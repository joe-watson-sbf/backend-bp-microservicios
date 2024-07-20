package com.joedev.accountservices.controller;

import com.joedev.accountservices.dto.ReporteEstadoCuenta;
import com.joedev.accountservices.services.ReporteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reportes")
public class ReporteController {

    private final ReporteServicio reporteServicio;

    @GetMapping
    public ResponseEntity<Object> generarReporte(
            @RequestParam Long clienteId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaFin
            ) {
        return ResponseEntity.ok(reporteServicio.generarReporteEstadoCuenta(clienteId, fechaInicio, fechaFin));
    }

}
