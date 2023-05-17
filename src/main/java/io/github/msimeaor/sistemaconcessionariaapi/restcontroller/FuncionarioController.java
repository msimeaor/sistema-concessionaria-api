package io.github.msimeaor.sistemaconcessionariaapi.restcontroller;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.FuncionarioDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.FuncionarioModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.FuncionarioServiceImpl;
import io.github.msimeaor.sistemaconcessionariaapi.exceptions.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/funcionario")
@RestController
public class FuncionarioController {

  private final FuncionarioServiceImpl funcionarioService;

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody @Valid FuncionarioDTO funcionarioDTO) {
    if (funcionarioService.existsByCpf(funcionarioDTO.getCpf())) {
      ErrorMessages errorMessages = new ErrorMessages("CPF JÁ CADASTRADO!");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessages.getMensagem());
    }

    if (funcionarioService.existsByEmail(funcionarioDTO.getEmail())) {
      ErrorMessages errorMessages = new ErrorMessages("EMAIL JÁ CADASTRADO!");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessages.getMensagem());
    }

    if (funcionarioService.existsByRg(funcionarioDTO.getRg())) {
      ErrorMessages errorMessages = new ErrorMessages("RG JÁ CADASTRADO!");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessages.getMensagem());
    }

    FuncionarioModel funcionarioModel = new FuncionarioModel();
    BeanUtils.copyProperties(funcionarioDTO, funcionarioModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.save(funcionarioModel));
  }

}
