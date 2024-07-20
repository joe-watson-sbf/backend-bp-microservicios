package com.joedev.clientservicios.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Entity test class for Cliente
 * Path: client-services/src/test/java/com/joedev/clientservicios/entity/ClienteTest.java
 * @Description: F5: Pruebas unitarias: Implementar 1 prueba unitaria para la entidad de dominio Cliente.
 *
 */
class ClienteTest {
    private Cliente cliente;
    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setContrasena("123456");
    }

    @Test
    void prePersist() {
        cliente.prePersist();
        assertTrue(cliente.getEstado());
    }
}