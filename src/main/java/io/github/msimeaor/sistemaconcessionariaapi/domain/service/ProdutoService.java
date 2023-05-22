package io.github.msimeaor.sistemaconcessionariaapi.domain.service;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ProdutoModel;

public interface ProdutoService {

  boolean existsByChassi(String chassi);
  ProdutoModel save(ProdutoModel produtoModel);

}
