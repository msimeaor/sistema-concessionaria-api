package io.github.msimeaor.sistemaconcessionariaapi.domain.service;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.ClienteDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ClienteModel;

public interface ClienteService {

  boolean existsByCpf(String cpf);
  boolean existsByRg(String rg);
  boolean existsByEmail(String email);
  ClienteModel save(ClienteDTO clienteDTO);

}
