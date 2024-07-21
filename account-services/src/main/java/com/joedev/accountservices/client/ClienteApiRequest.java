package com.joedev.accountservices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Component
@FeignClient(name = "client-services", url = "http://localhost:8082/api/clientes")
public interface ClienteApiRequest {

    @RequestMapping(value = "{clienteId}",  method = RequestMethod.GET)
    Optional<ClienteModel> findById(@PathVariable Long clienteId);

}
