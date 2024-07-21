package com.joedev.clientservicios.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Component
@FeignClient(name = "account-services", url = "http://localhost:8080/api/cuentas")
public interface AccountApiRequest {

    @RequestMapping(value = "cliente/{clienteId}",  method = RequestMethod.DELETE)
    void deleteAccountByClientId(@PathVariable Long clienteId);
}
