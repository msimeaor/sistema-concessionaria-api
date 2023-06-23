package io.github.msimeaor.sistemaconcessionariaapi.domain.repository;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ItemPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoModel, UUID> {

  @Query("Select i from ItemPedidoModel i where i.id = UUID_TO_BIN(:id)")
  Optional<ItemPedidoRepository> findById(@Param("id") String id);

}
