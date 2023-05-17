package io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.FuncionarioModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.repository.FuncionarioRepository;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class FuncionarioServiceImpl implements FuncionarioService {

  private final FuncionarioRepository funcionarioRepository;

  public boolean existsByCpf(String cpf) {
    return funcionarioRepository.existsByCpf(cpf);
  }

  public boolean existsByEmail(String email) {
    return funcionarioRepository.existsByEmail(email);
  }

  public boolean existsByRg(String rg) {
    return funcionarioRepository.existsByRg(rg);
  }

  @Transactional
  public FuncionarioModel save(FuncionarioModel funcionarioModel) {
    return funcionarioRepository.save(funcionarioModel);
  }

}
