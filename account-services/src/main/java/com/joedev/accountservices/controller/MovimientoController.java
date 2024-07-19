package com.joedev.accountservices.controller;

import com.joedev.accountservices.dto.MovimientoDto;
import com.joedev.accountservices.services.MovimientoServicios;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/movimientos")
public class MovimientoController {
    private final MovimientoServicios servicios;

    @GetMapping
    public ResponseEntity<List<MovimientoDto>> findAll() {
        return ResponseEntity.ok(servicios.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(servicios.findById(id));
    }

    @PostMapping
    public ResponseEntity<MovimientoDto> save(MovimientoDto movimientoDto) {
        return ResponseEntity.ok(servicios.save(movimientoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicios.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, MovimientoDto movimientoDto) {
        servicios.update(id, movimientoDto);
        return ResponseEntity.ok().build();
    }
}
