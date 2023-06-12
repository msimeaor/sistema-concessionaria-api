package io.github.msimeaor.sistemaconcessionariaapi.restcontroller;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.ClienteDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ClienteModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.ClienteServiceImpl;
import io.github.msimeaor.sistemaconcessionariaapi.exceptions.ErrorMessages;
import io.swagger.annotations.*;
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
@Api("API DE CLIENTES")
public class ClienteController {

  private final ClienteServiceImpl clienteService;

  @PostMapping
  @ApiOperation("SALVAR NO BANCO DE DADOS")
  @ApiResponses({
    @ApiResponse(code = 409, message = "Cliente já cadastrado"),
    @ApiResponse(code = 403, message = "Funcionário não autorizado"),
    @ApiResponse(code = 201, message = "Cliente salvo com sucesso")
  })
  public ResponseEntity<Object> save(@RequestBody @Valid ClienteDTO clienteDTO) {
    if (clienteService.existsByCpf(clienteDTO.getCpf())) {
      ErrorMessages errorMessages = new ErrorMessages("CPF JÁ CADASTRADO!");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessages.getMensagem());
    }

    if (clienteService.existsByEmail(clienteDTO.getEmail())) {
      ErrorMessages errorMessages = new ErrorMessages("EMAIL JÁ CADASTRADO!");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessages.getMensagem());
    }

    if (clienteService.existsByRg(clienteDTO.getRg())) {
      ErrorMessages errorMessages = new ErrorMessages("RG JÁ CADASTRADO!");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessages.getMensagem());
    }

    ClienteModel clienteModel = instanciarESetarPropriedades(clienteDTO, ClienteModel.class);
    return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.save(clienteModel));
  }

  @PutMapping("/{id}")
  @ApiOperation("ATUALIZA OS DADOS")
  @ApiResponses({
    @ApiResponse(code = 404, message = "Cliente não encontrado"),
    @ApiResponse(code = 403, message = "Funcionário não autorizado"),
    @ApiResponse(code = 200, message = "Cliente atualizado com sucesso")
  })
  public ResponseEntity<Object> update(@RequestBody @Valid ClienteDTO clienteDTO,
                                       @PathVariable(name = "id") @ApiParam("ID DO CLIENTE") UUID id) {

    Optional<ClienteModel> clienteModelOptional = clienteService.getById(id);
    if (!clienteModelOptional.isPresent()) {
      ErrorMessages errorMessages = new ErrorMessages("CLIENTE NÃO ENCONTRADO");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessages.getMensagem());
    }

    ClienteModel clienteModel = instanciarESetarPropriedades(clienteDTO, ClienteModel.class);
    clienteModel.setId(clienteModelOptional.get().getId());
    return ResponseEntity.status(HttpStatus.OK).body(clienteService.save(clienteModel));
  }

  @GetMapping("/{cpf}")
  @ApiOperation("BUSCA POR CPF")
  @ApiResponses({
    @ApiResponse(code = 404, message = "Cliente não encontrado"),
    @ApiResponse(code = 403, message = "Funcionário não autorizado"),
    @ApiResponse(code = 200, message = "Cliente encontrado")
  })
  public ResponseEntity<Object> getByCpf(@PathVariable(name = "cpf") @ApiParam("CPF DO CLIENTE") String cpf) {
    Optional<ClienteModel> clienteModelOptional = clienteService.getByCpf(cpf);
    if (!clienteModelOptional.isPresent()) {
      ErrorMessages errorMessages = new ErrorMessages("CLIENTE NÃO ENCONTRADO!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessages.getMensagem());
    }

    return ResponseEntity.status(HttpStatus.OK).body(clienteModelOptional.get());
  }

  public static <T, R> R instanciarESetarPropriedades(T dto, Class<R> classeRetorno) {
    R instancia = null;
     try {

       instancia = classeRetorno.getDeclaredConstructor().newInstance();
       BeanUtils.copyProperties(dto, instancia);

     } catch (Exception e) {
       System.out.println(e.getMessage());
     }

     return instancia;
  }

}
