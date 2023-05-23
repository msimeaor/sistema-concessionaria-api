package io.github.msimeaor.sistemaconcessionariaapi.domain.service;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ProdutoModel;

import java.util.Optional;
import java.util.UUID;

public interface ProdutoService {

  boolean existsByChassi(String chassi);
  ProdutoModel save(ProdutoModel produtoModel);
  boolean existsByPlaca(String placa);
  Optional<ProdutoModel> getByChassi(String chassi);
  Optional<ProdutoModel> getById(UUID id);

}
