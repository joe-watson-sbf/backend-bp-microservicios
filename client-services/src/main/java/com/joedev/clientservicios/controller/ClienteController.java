package com.joedev.clientservicios.controller;

import com.joedev.clientservicios.dto.ClienteDto;
import com.joedev.clientservicios.services.ClienteServicios;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteServicios clientService;

    @GetMapping
    public List<ClienteDto> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> saveClient(@RequestBody ClienteDto clienteDto) {
        return ResponseEntity.ok(clientService.save(clienteDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateClient(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        clientService.update(id, clienteDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
