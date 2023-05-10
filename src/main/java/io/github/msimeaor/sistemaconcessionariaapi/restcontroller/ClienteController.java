package io.github.msimeaor.sistemaconcessionariaapi.restcontroller;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.ClienteDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ClienteModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.ClienteServiceImpl;
import io.github.msimeaor.sistemaconcessionariaapi.exceptions.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cliente")
public class ClienteController {

  private final ClienteServiceImpl clienteService;

  @PostMapping
  public ResponseEntity<Object> save(@RequestBody @Valid ClienteDTO clienteDTO) {
    if (clienteService.existsByCpf(clienteDTO.getCpf())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("CPF JÁ CADASTRADO!");
    }

    if (clienteService.existsByEmail(clienteDTO.getEmail())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("EMAIL JÁ CADASTRADO!");
    }

    if (clienteService.existsByRg(clienteDTO.getRg())) {
      return ResponseEntity.status(HttpStatus.CONFLICT).body("RG JÁ CADASTRADO!");
    }

    ClienteModel clienteModel = new ClienteModel();
    BeanUtils.copyProperties(clienteDTO, clienteModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteModel));
  }

  @PutMapping("/{id}")
  public ResponseEntity<Object> update(@RequestBody @Valid ClienteDTO clienteDTO,
                                       @PathVariable(name = "id") UUID id) {

    Optional<ClienteModel> clienteModelOptional = clienteService.getById(id);
    if (!clienteModelOptional.isPresent()) {
      ErrorMessages errorMessages = new ErrorMessages("CLIENTE NÃO ENCONTRADO");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessages.getMensagem());
    }

    ClienteModel clienteModel = new ClienteModel();
    BeanUtils.copyProperties(clienteDTO, clienteModel);
    clienteModel.setId(clienteModelOptional.get().getId());
    return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(clienteModel));
  }

}
