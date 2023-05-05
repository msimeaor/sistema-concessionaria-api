package io.github.msimeaor.sistemaconcessionariaapi.domain.repository;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {

  boolean existsByCpf(String cpf);
  boolean existsByEmail(String email);
  boolean existsByRg(String rg);

}
