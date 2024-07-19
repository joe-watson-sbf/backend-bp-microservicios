package com.joedev.accountservices.services;

import com.joedev.accountservices.dto.MovimientoDto;
import com.joedev.accountservices.entity.Movimiento;
import com.joedev.accountservices.repository.MovimientoRepository;
import exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import respositories.BaseCrudServices;

import java.util.List;

import static utils.Utils.requireNonNull;

@Service
@RequiredArgsConstructor
public class MovimientoServicios implements BaseCrudServices<MovimientoDto, Long> {
    private final MovimientoRepository movimientoRepository;
    private final ModelMapper mapper;


    @Override
    public List<MovimientoDto> findAll() {
        return movimientoRepository.findAll()
                .stream()
                .map(movimiento -> mapper.map(movimiento, MovimientoDto.class))
                .toList();
    }

    @Override
    public MovimientoDto findById(Long id) {
        requireNonNull(id, "El id no puede ser nulo");
        return mapper.map(getMovimiento(id), MovimientoDto.class);
    }

    @Override
    public MovimientoDto save(MovimientoDto movimientoDto) {
        requireNonNull(movimientoDto, "Los datos del movimiento no pueden ser nulos");
        Movimiento movimiento = mapper.map(movimientoDto, Movimiento.class);
        return mapper.map(movimientoRepository.save(movimiento), MovimientoDto.class);
    }

    @Override
    public void delete(Long id) {
        requireNonNull(id, "El id no puede ser nulo");
        movimientoRepository.deleteById(id);
    }

    @Override
    public void update(Long aLong, MovimientoDto movimientoDto) {
        requireNonNull(aLong, "El id no puede ser nulo");
        requireNonNull(movimientoDto, "Los datos del movimiento no pueden ser nulos");
        Movimiento movimiento = getMovimiento(aLong);
        movimiento.setFecha(movimientoDto.getFecha());
        movimiento.setSaldo(movimientoDto.getSaldo());
        movimiento.setValor(movimientoDto.getValor());
        movimiento.setTipo(movimientoDto.getTipo());
        movimientoRepository.save(movimiento);
    }


    private Movimiento getMovimiento(Long id) {
        return movimientoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Movimiento no encontrado"));
    }
}
