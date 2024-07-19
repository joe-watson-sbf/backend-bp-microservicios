package com.joedev.accountservices.services;

import com.joedev.accountservices.dto.CuentaDto;
import com.joedev.accountservices.dto.MovimientoDto;
import com.joedev.accountservices.entity.Cuenta;
import com.joedev.accountservices.entity.Movimiento;
import com.joedev.accountservices.entity.TipoDeMovimiento;
import com.joedev.accountservices.repository.CuentaRepository;
import com.joedev.accountservices.services.cliente.ClienteApi;
import exceptions.BusinessException;
import exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import respositories.BaseCrudServices;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class CuentaServicios implements BaseCrudServices<CuentaDto, Long> {
    private final CuentaRepository cuentaRepository;
    private final ModelMapper mapper;
    private final ClienteApi clienteApi;


    @Override
    public List<CuentaDto> findAll() {
        return cuentaRepository.findAll()
                .stream()
                .map(account -> mapper.map(account, CuentaDto.class))
                .toList();
    }

    @Override
    public CuentaDto findById(Long id) {
        requireNonNull(id, "El id no puede ser nulo");
        return mapper.map(getCuenta(id), CuentaDto.class);
    }

    @Override
    public CuentaDto save(CuentaDto cuentaDto) {
        requireNonNull(cuentaDto, "Los datos de la cuenta no pueden ser nulos");
        requireNonNull(cuentaDto.getClienteId(), "El id del cliente no puede ser nulo");

        /*
         * Aqui se puede hacer verificaciones adicionales antes de guardar la cuenta
         * si existe realmente el cliente con el id proporcionado,
         * pero to-do depende de la lógica de negocio
         * */
        if(!clienteApi.existeCliente(cuentaDto.getClienteId())){
            throw new BusinessException("El cliente con el id: " + cuentaDto.getClienteId() + " no existe");
        }

        Cuenta cuenta = mapper.map(cuentaDto, Cuenta.class);
        return mapper.map(cuentaRepository.save(cuenta), CuentaDto.class);
    }


    @Override
    public void delete(Long id) {
        requireNonNull(id, "El id no puede ser nulo");
        cuentaRepository.deleteById(id);
    }

    @Override
    public void update(Long id, CuentaDto cuentaDto) {
        requireNonNull(id, "El id no puede ser nulo");
        requireNonNull(cuentaDto, "Los datos de la cuenta no pueden ser nulos");
        Cuenta cuenta = getCuenta(id);
        cuenta.setTipo(cuentaDto.getTipo());
        cuenta.setSaldoInicial(cuentaDto.getSaldoInicial());
        cuentaRepository.save(cuenta);
    }

    @Transactional
    public MovimientoDto registrarMovimiento(Long cuentaId, BigDecimal valor) {
        // antes de hacer cualquier operación es importante verificar el valor ingresado
        requiereValorDiferenteDeCero(valor);

        Cuenta cuenta = getCuenta(cuentaId);

        // Va lanzar un error si no tiene fondo disponible
        cuenta.asegurarQueTieneFondoDisponible(valor);

        /*
         * El tipo de movimiento se define dependiendo del valor ingresa
         *  Si es positivo es un Deposito
         * */
        TipoDeMovimiento tipoMovimiento = valor.compareTo(BigDecimal.ZERO) > 0 ? TipoDeMovimiento.DEPOSITO : TipoDeMovimiento.RETIRO;

        Movimiento movimiento = Movimiento.builder()
                .fecha(LocalDate.now())
                .tipo(tipoMovimiento)
                .valor(valor)
                .saldo(cuenta.getSaldoInicial().add(valor))
                .build();
        cuenta.agregarMovimiento(movimiento);
        cuentaRepository.save(cuenta);
        return mapper.map(movimiento, MovimientoDto.class);
    }

    private void requiereValorDiferenteDeCero(BigDecimal valorIngresado){
        if(valorIngresado.equals(BigDecimal.ZERO)){
            throw new BusinessException("El valor de la transación no puede ser 0!");
        }
    }



    private Cuenta getCuenta(Long id) {
        return cuentaRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("No se encontró la cuenta con el id: " + id)
                );
    }
}