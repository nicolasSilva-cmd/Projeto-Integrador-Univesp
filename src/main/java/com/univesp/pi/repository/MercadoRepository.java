package com.univesp.pi.repository;

import com.univesp.pi.model.MercadoEntity;
import com.univesp.pi.service.MercadoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MercadoRepository extends JpaRepository<MercadoEntity, Long> {

    Optional<MercadoEntity> findByNome(String nome);

    List<MercadoEntity> findByEndereco(String endereco);

//    @Query("SELECT e.endereco FROM MercadoEntity e WHERE e.endereco LIKE CONCAT('%',:username,'%')")
//    List<MercadoEntity> findMercadoWhereEnderecoLike(@Param("endereco") String endereco);

    List<MercadoEntity> findByEnderecoContaining(String endereco);

    List<MercadoEntity> findByNomeContainingIgnoreCase(String nome);


}
