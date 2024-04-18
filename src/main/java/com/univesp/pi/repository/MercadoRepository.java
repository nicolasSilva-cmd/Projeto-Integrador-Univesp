package com.univesp.pi.repository;

import com.univesp.pi.model.MercadoEntity;
import com.univesp.pi.service.MercadoService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MercadoRepository extends JpaRepository<MercadoEntity, Long> {

    Optional<MercadoEntity> findByNome(String nome);

    List<MercadoEntity> findByEndereco(String endereco);

}
