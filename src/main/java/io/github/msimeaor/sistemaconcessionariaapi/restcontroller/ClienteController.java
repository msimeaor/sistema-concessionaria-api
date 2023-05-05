package io.github.msimeaor.sistemaconcessionariaapi.restcontroller;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.ClienteDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.ClienteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteDTO));
  }

}
