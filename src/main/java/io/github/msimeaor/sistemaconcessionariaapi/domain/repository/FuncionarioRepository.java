package io.github.msimeaor.sistemaconcessionariaapi.domain.repository;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, UUID> {

  boolean existsByCpf(String cpf);
  boolean existsByEmail(String email);
  boolean existsByRg(String rg);
  Optional<FuncionarioModel> findByCpf(String cpf);
  Optional<FuncionarioModel> findByEmail(String email);

}
