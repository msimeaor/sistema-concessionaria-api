package io.github.msimeaor.sistemaconcessionariaapi.domain.service;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.FuncionarioModel;

public interface FuncionarioService {

  boolean existsByCpf(String cpf);
  boolean existsByEmail(String email);
  boolean existsByRg(String rg);
  FuncionarioModel save(FuncionarioModel funcionarioModel);

}
