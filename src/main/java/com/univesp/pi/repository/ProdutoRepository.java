package com.univesp.pi.repository;

import com.univesp.pi.model.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Long> {

    Optional<ProdutoEntity> findAllByNome(String nome);

    List<ProdutoEntity> findAllByDescontoIsTrue();
}
