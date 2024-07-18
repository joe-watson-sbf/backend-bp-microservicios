package com.joedev.clientservicios.services;

import com.joedev.clientservicios.dto.ClienteDto;

import java.util.List;

public interface ClientService {
    List<ClienteDto> findAll();
    ClienteDto findById(Long id);
    ClienteDto save(ClienteDto clienteDto);
    void delete(Long id);
    void update(Long id, ClienteDto clienteDto);
}
