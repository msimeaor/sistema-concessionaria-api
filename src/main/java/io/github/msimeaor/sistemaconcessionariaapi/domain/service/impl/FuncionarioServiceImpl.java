package io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl;

import io.github.msimeaor.sistemaconcessionariaapi.domain.model.FuncionarioModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.repository.FuncionarioRepository;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.FuncionarioService;
import io.github.msimeaor.sistemaconcessionariaapi.exceptions.ExceptionLancada;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FuncionarioServiceImpl implements UserDetailsService, FuncionarioService {

  @Autowired
  private PasswordEncoder passwordEncoder;

  private final FuncionarioRepository funcionarioRepository;


  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<FuncionarioModel> funcionarioModelOptional = funcionarioRepository.findByEmail(email);
    if (!funcionarioModelOptional.isPresent()) {
      throw new ExceptionLancada("USUÁRIO NÃO EXISTENTE!");
    }

    FuncionarioModel funcionarioModel = funcionarioModelOptional.get();

    String[] roles = (funcionarioModel.getAcesso() == "ADMIN") ? new String[]{"USER", "ADMIN"} : new String[]{"USER"};

    return User
      .builder()
      .username(funcionarioModel.getEmail())
      .roles(roles)
      .password(funcionarioModel.getSenha())
      .build();
  }

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

  public Optional<FuncionarioModel> getByCpf(String cpf) {
    return funcionarioRepository.findByCpf(cpf);
  }

  public Optional<FuncionarioModel> getById(UUID id) {
    return funcionarioRepository.findById(id);
  }

  @Transactional
  public void deletar(FuncionarioModel funcionarioModel) {
    funcionarioRepository.delete(funcionarioModel);
  }
}
