package com.joedev.accountservices.repository;

import com.joedev.accountservices.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long>{
    Optional<Cuenta> findCuentaByNumero(Long numero);
}
