package io.github.msimeaor.sistemaconcessionariaapi.domain.service;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.FuncionarioModel;

import java.util.Optional;
import java.util.UUID;

public interface FuncionarioService {

  boolean existsByCpf(String cpf);
  boolean existsByEmail(String email);
  boolean existsByRg(String rg);
  FuncionarioModel save(FuncionarioModel funcionarioModel);
  Optional<FuncionarioModel> getByCpf(String cpf);
  Optional<FuncionarioModel> getById(UUID id);

}
