package io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl;

import io.github.msimeaor.sistemaconcessionariaapi.domain.repository.FornecedorRepository;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FornecedorServiceImpl implements FornecedorService {

  private final FornecedorRepository fornecedorRepository;

}
