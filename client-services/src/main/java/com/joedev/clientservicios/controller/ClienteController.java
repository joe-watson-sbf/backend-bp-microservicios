package com.joedev.clientservicios.controller;

import com.joedev.clientservicios.dto.ClienteDto;
import com.joedev.clientservicios.exceptions.ResponseMessage;
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
    public ResponseEntity<ResponseMessage> updateClient(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
        clientService.update(id, clienteDto);
        return ResponseEntity.ok(new ResponseMessage("Cliente actualizado correctamente", 200));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> deleteClient(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.ok(new ResponseMessage("Cliente eliminado correctamente", 200));
    }
}
