package com.joedev.clientservicios.services;

import com.joedev.clientservicios.dto.ClienteDto;
import com.joedev.clientservicios.entity.Cliente;
import com.joedev.clientservicios.exceptions.BusinessException;
import com.joedev.clientservicios.exceptions.NotFoundException;
import com.joedev.clientservicios.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteServicios {
    private final ClienteRepository clienteRepository;
    private final ModelMapper mapper;

    public List<ClienteDto> findAll() {
        return clienteRepository.findAll().stream()
                .map(cliente -> mapper.map(cliente, ClienteDto.class))
                .toList();
    }

    public ClienteDto findById(Long id) {
        requireNonNull(id, "El id del cliente es requerido!");
        return clienteRepository.findById(id)
                .map(cliente -> mapper.map(cliente, ClienteDto.class))
                .orElseThrow(
                        () -> new NotFoundException("Cliente no encontrado")
                );
    }

    public ClienteDto save(ClienteDto clienteDto) {
        requireNonNull(clienteDto, "Los datos del cliente son requeridos!");
        requireNonNull(clienteDto.getNombre(), "El nombre del cliente es requerido!");
        requireNonNull(clienteDto.getIdentificacion(), "La identificación del cliente es requerida!");
        return mapper.map(
                clienteRepository.save(
                        mapper.map(clienteDto, Cliente.class)
                ),
                ClienteDto.class
        );
    }

    public void delete(Long id) {
        requireNonNull(id, "El id del cliente es requerido!");
        clienteRepository.deleteById(id);
    }

    public void update(Long id, ClienteDto clienteDto) {
        requireNonNull(id, "El id del cliente es requerido!");
        requireNonNull(clienteDto, "Los datos del cliente son requeridos!");
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Cliente no encontrado para actualizar su información")
                );
        cliente.setNombre(clienteDto.getNombre());
        cliente.setEdad(clienteDto.getEdad());
        cliente.setGenero(clienteDto.getGenero());
        cliente.setContrasena(clienteDto.getContrasena());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setTelefono(clienteDto.getTelefono());
        cliente.setIdentificacion(clienteDto.getIdentificacion());

        clienteRepository.save(cliente);
    }

    private void requireNonNull(Object obj, String message) {
        if (obj == null) {
            throw new BusinessException(message);
        }
    }

}
