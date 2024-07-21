package com.joedev.clientservicios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ClienteServiciosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClienteServiciosApplication.class, args);
    }

}
