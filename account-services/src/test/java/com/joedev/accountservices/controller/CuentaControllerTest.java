package com.joedev.accountservices.controller;

import com.joedev.accountservices.entity.Cuenta;
import com.joedev.accountservices.entity.TipoCuenta;
import com.joedev.accountservices.repository.CuentaRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class CuentaControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CuentaRepository cuentaRepository;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("pgvector/pgvector:pg16")
            .withDatabaseName("account-test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
        registry.add("spring.jpa.properties.hibernate.dialect", () -> "org.hibernate.dialect.PostgreSQLDialect");
        registry.add("spring.jpa.show-sql", () -> "true");
        registry.add("spring.datasource.driverClassName", () -> "org.postgresql.Driver");
        registry.add("spring.jpa.defer-datasource-initialization", () -> "true");
        registry.add("spring.sql.init.mode", () -> "embedded");
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
    @Sql(scripts = {"/init-schema-test.sql"}, executionPhase = BEFORE_TEST_METHOD)
    void findAll() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/cuentas"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertEquals(200, mvcResult.getResponse().getStatus());
     }

    @Test
    void findById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/cuentas/203823"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

    @Test
    void save() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/cuentas")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                  {
                    "tipo": "CORRIENTE",
                    "saldoInicial": 8911.0
                   }
                  """)
                )
                .andDo(print())
                .andExpect(jsonPath("$.tipo").value("CORRIENTE"))
                .andExpect(status().isOk()).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private List<Cuenta> cuentas() {
        return List.of(
                new Cuenta(1L, 34567890, TipoCuenta.AHORROS, BigDecimal.valueOf(1000.0), true, List.of()),
                new Cuenta(2L, 12345678, TipoCuenta.CORRIENTE, BigDecimal.valueOf(2000.0), true, List.of()),
                new Cuenta(3L, 56789012, TipoCuenta.AHORROS, BigDecimal.valueOf(3000.0), true, List.of()),
                new Cuenta(4L, 90123456, TipoCuenta.CORRIENTE, BigDecimal.valueOf(4000.0), true, List.of())
        );
    }



}