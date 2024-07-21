package com.joedev.accountservices.controller;

import com.joedev.accountservices.dto.CuentaDto;
import com.joedev.accountservices.exceptions.ResponseMessage;
import com.joedev.accountservices.services.CuentaServicios;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cuentas")
public class CuentaController {
    private final CuentaServicios servicios;

    @GetMapping
    public ResponseEntity<List<CuentaDto>> findAll() {
        return ResponseEntity.ok(servicios.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(servicios.findById(id));
    }

    @PostMapping
    public ResponseEntity<CuentaDto> save(@RequestBody CuentaDto cuentaDto) {
        return ResponseEntity.ok(servicios.save(cuentaDto));
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> registrarMovimiento(
            @RequestParam Long numeroCuenta,
            @RequestParam BigDecimal saldo
            ) {
        servicios.registrarMovimiento(numeroCuenta, saldo);
        return ResponseEntity.ok(
                new ResponseMessage("Movimiento registrado", 200)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id) {
        servicios.delete(id);
        return ResponseEntity.ok(
                new ResponseMessage("Cuenta eliminada", 200)
        );
    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<ResponseMessage> deleteByClientId(@PathVariable Long id) {
        servicios.deleteByClientId(id);
        return ResponseEntity.ok(
                new ResponseMessage("Cuentas eliminadas", 200)
        );
    }

}
