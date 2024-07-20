package com.joedev.accountservices.services.cliente;

import exceptions.BusinessException;
import exceptions.ResponseMessage;
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
        return findById(clienteId).isPresent();
    }

    @Override
    public Optional<ClienteModel> findById(Long clientId) {
        return webClient.get()
                .uri("/clientes/{id}", clientId)
                .retrieve()
                .onStatus(status -> status.is4xxClientError() || status.is5xxServerError(),
                        response -> response.bodyToMono(ResponseMessage.class).map(
                                responseMessage -> new BusinessException(responseMessage.getMessage())
                        )
                )
                .bodyToMono(ClienteModel.class)
                .blockOptional();

    }
}
