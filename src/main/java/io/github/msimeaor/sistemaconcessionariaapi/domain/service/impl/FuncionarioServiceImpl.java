package io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl;

import io.github.msimeaor.sistemaconcessionariaapi.domain.repository.FuncionarioRepository;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FuncionarioServiceImpl implements FuncionarioService {

  private final FuncionarioRepository funcionarioRepository;

}
