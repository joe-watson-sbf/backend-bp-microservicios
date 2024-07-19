package com.joedev.accountservices.services.cliente;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClienteHelper implements ClienteApi {

    private final WebClient webClient;

    @Override
    public boolean existeCliente(Long clienteId){
        return Boolean.TRUE.equals(webClient.get()
                .uri("/clientes/{id}", clienteId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block());
    }

    @Override
    public Optional<ClienteModel> findById(Long clientId) {
        return webClient.get()
                .uri("/clientes/{id}", clientId)
                .retrieve()
                .bodyToMono(ClienteModel.class)
                .blockOptional();
    }
}
