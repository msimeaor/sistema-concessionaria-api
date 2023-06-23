package io.github.msimeaor.sistemaconcessionariaapi.domain.repository;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID> {

  @Query("Select p from ProdutoModel p where p.id = UUID_TO_BIN(:id)")
  Optional<ProdutoModel> findById(@Param("id") String id);
  boolean existsByChassi(String chassi);
  boolean existsByPlaca(String placa);
  Optional<ProdutoModel> findByChassi(String chassi);

}
