package io.github.msimeaor.sistemaconcessionariaapi.domain.repository;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID> {

  boolean existsByChassi(String chassi);

}
