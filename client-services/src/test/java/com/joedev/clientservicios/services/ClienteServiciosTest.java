package com.joedev.clientservicios.services;

import com.joedev.clientservicios.entity.Cliente;
import com.joedev.clientservicios.repository.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Sql({"/init-schema-test.sql"})
class ClienteServiciosTest {

    @MockBean
    private ClienteRepository repository;
    @MockBean
    private ModelMapper mapper;

    @Test
    void findAll() {
        List<Cliente> clientes = repository.findAll();
        assertEquals(0, clientes.size());
    }

    @Test
    void findById() {

    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }
}