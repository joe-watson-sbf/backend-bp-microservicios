package com.joedev.clientservicios.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("pgvector/pgvector:pg16")
            .withDatabaseName("cliente-db-test")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("init-schema-test.sql");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {

        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "update");
        registry.add("spring.jpa.defer-datasource-initialization", () -> "true");
        registry.add("spring.jpa.show-sql", () -> "true");
        registry.add("spring.datasource.driverClassName", () -> "org.postgresql.Driver");
        registry.add("spring.jpa.defer-datasource-initialization", () -> "true");
        registry.add("spring.sql.init.mode", () -> "embedded");
        registry.add("spring.batch.jdbc.initialize-schema", () -> "always");
    }

    @BeforeEach
    void setUp() {
        postgreSQLContainer.start();
    }

    @AfterEach
    void tearDown() {
        postgreSQLContainer.stop();
    }

    @Test
    void saveClientSuccessfully() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "nombre":"Joe Watson SBF",
                              "genero":"MASCULINO",
                              "edad":80,
                              "identificacion":"451234567",
                              "direccion":"Calle Falsa 09",
                              "telefono":"+7223456789",
                              "contrasena":"teestoiteqw"
                            }
                  """)
                )
                .andDo(print())
                .andExpect(jsonPath("$.edad").value(80))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(status().isOk()).andReturn();
    }

    @Test
    void saveClientWithoutName() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                              "genero":"MASCULINO",
                              "edad":80,
                              "identificacion":"451234567",
                              "direccion":"Calle Falsa 09",
                              "telefono":"+7223456789",
                              "contrasena":"teestoiteqw"
                            }
                  """)
                )
                .andDo(print())
                .andExpect(jsonPath("$.message").value("El nombre del cliente es requerido!"))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(status().isBadRequest()).andReturn();
    }

}