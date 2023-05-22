package io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ProdutoModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.repository.ProdutoRepository;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProdutoServiceImpl implements ProdutoService {

  private final ProdutoRepository produtoRepository;

  public boolean existsByChassi(String chassi) {
    return produtoRepository.existsByChassi(chassi);
  }

  public ProdutoModel save(ProdutoModel produtoModel) {
    return produtoRepository.save(produtoModel);
  }

}
