package io.github.msimeaor.sistemaconcessionariaapi.domain.service;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ClienteModel;

import java.util.Optional;
import java.util.UUID;

public interface ClienteService {

  boolean existsByCpf(String cpf);
  boolean existsByRg(String rg);
  boolean existsByEmail(String email);
  ClienteModel save(ClienteModel clienteModel);
  Optional<ClienteModel> getById(UUID id);
  Optional<ClienteModel> getByCpf(String cpf);
}
