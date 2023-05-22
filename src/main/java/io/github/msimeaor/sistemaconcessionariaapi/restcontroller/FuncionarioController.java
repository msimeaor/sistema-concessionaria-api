package io.github.msimeaor.sistemaconcessionariaapi.restcontroller;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.FuncionarioDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.FuncionarioModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.FuncionarioServiceImpl;
import io.github.msimeaor.sistemaconcessionariaapi.exceptions.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

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

    FuncionarioModel funcionarioModel = ClienteController.instanciarESetarPropriedades(funcionarioDTO, FuncionarioModel.class);
    return ResponseEntity.status(HttpStatus.CREATED).body(funcionarioService.save(funcionarioModel));
  }

  @GetMapping("/{CPF}")
  public ResponseEntity<Object> getByCpf(@PathVariable(name = "CPF") String cpf) {
    Optional<FuncionarioModel> funcionarioModelOptional = funcionarioService.getByCpf(cpf);
    if (!(funcionarioModelOptional.isPresent())) {
      ErrorMessages errorMessages = new ErrorMessages("FUNCIONÁRIO NÃO ENCONTRADO!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessages.getMensagem());
    }

    return ResponseEntity.status(HttpStatus.OK).body(funcionarioModelOptional.get());
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@PathVariable(name = "id") UUID id,
                                       @RequestBody @Valid FuncionarioDTO funcionarioDTO) {

    Optional<FuncionarioModel> funcionarioModelOptional = funcionarioService.getById(id);
    if (!(funcionarioModelOptional.isPresent())) {
      ErrorMessages errorMessages = new ErrorMessages("FUNCIONÁRIO NÃO ENCONTRADO!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessages.getMensagem());
    }

    FuncionarioModel funcionarioModel = ClienteController.instanciarESetarPropriedades(funcionarioDTO, FuncionarioModel.class);
    funcionarioModel.setId(funcionarioModelOptional.get().getId());
    return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.save(funcionarioModel));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable(name = "id") UUID id) {
    Optional<FuncionarioModel> funcionarioModelOptional = funcionarioService.getById(id);
    if (!(funcionarioModelOptional.isPresent())) {
      ErrorMessages errorMessages = new ErrorMessages("CLIENTE NÃO ENCONTRADO!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessages.getMensagem());
    }

    funcionarioService.deletar(funcionarioModelOptional.get());
    return ResponseEntity.status(HttpStatus.OK).body("FUNCIONÁRIO DELETADO COM SUCESSO!");

  }

}
