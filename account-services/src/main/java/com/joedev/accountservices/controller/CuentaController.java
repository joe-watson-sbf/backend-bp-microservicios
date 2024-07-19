package com.joedev.accountservices.controller;

import com.joedev.accountservices.dto.CuentaDto;
import com.joedev.accountservices.services.CuentaServicios;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cuentas")
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
    public ResponseEntity<CuentaDto> save(CuentaDto cuentaDto) {
        return ResponseEntity.ok(servicios.save(cuentaDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, CuentaDto cuentaDto) {
        servicios.update(id, cuentaDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicios.delete(id);
        return ResponseEntity.ok().build();
    }

}
