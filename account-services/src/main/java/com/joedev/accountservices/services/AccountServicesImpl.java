package com.joedev.accountservices.services;

import com.joedev.accountservices.dto.CuentaDto;
import com.joedev.accountservices.repository.AccountRepository;
import com.joedev.accountservices.repository.MovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServicesImpl implements AccountServices {
    private final AccountRepository accountRepository;
    private final MovimientoRepository movimientoRepository;
    @Override
    public void createAccount(CuentaDto cuentaDto) {

    }
}
