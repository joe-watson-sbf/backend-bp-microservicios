package com.joedev.accountservices.services.cliente;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface ClienteApi {
    boolean existeCliente(Long clienteId);
    Optional<ClienteModel> findById(Long clientId);
}
