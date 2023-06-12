package io.github.msimeaor.sistemaconcessionariaapi.restcontroller;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.ProdutoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ProdutoModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.ProdutoServiceImpl;
import io.github.msimeaor.sistemaconcessionariaapi.exceptions.ErrorMessages;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/produto")
@Api("API DE PRODUTOS")
public class ProdutoController {

  private final ProdutoServiceImpl produtoService;


  @PostMapping
  @ApiOperation("SALVA NO BANCO DE DADOS")
  @ApiResponses({
    @ApiResponse(code = 403, message = "Funcionário não autorizado"),
    @ApiResponse(code = 409, message = "Produto já existente"),
    @ApiResponse(code = 201, message = "Produto cadastrado com sucesso")
  })
  public ResponseEntity<Object> save(@RequestBody @Valid ProdutoDTO produtoDTO) {
    if (produtoService.existsByChassi(produtoDTO.getChassi())) {
      ErrorMessages chassiCadastrado = new ErrorMessages("CHASSI JÁ CADASTRADO!");
      return ResponseEntity.status(HttpStatus.CONFLICT).body(chassiCadastrado.getMensagem());
    }

    if (!(produtoDTO.getPlaca().isEmpty() || produtoDTO.getPlaca().isBlank())) {
      if (produtoService.existsByPlaca(produtoDTO.getPlaca())) {
        ErrorMessages placaCadastrada = new ErrorMessages("PLACA JÁ CADASTRADA!");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(placaCadastrada.getMensagem());
      }
    }

    ProdutoModel produtoModel = ClienteController.instanciarESetarPropriedades(produtoDTO, ProdutoModel.class);
    return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoModel));

  }

  @GetMapping("/{chassi}")
  @ApiOperation("BUSCA POR CHASSI")
  @ApiResponses({
    @ApiResponse(code = 403, message = "Funcionário não autorizado"),
    @ApiResponse(code = 404, message = "Produto não encontrado"),
    @ApiResponse(code = 200, message = "Produto encontrado")
  })
  public ResponseEntity<Object> getByChassi(@PathVariable(name = "chassi") @ApiParam("CHASSI DO CARRO") String chassi) {
    Optional<ProdutoModel> produtoModelOptional = produtoService.getByChassi(chassi);
    if (!produtoModelOptional.isPresent()) {
      ErrorMessages chassiNull = new ErrorMessages("CHASSI NÃO CADASTRADO!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(chassiNull.getMensagem());
    }

    return ResponseEntity.status(HttpStatus.OK).body(produtoModelOptional.get());

  }

  @PutMapping("/{id}")
  @ApiOperation("ATUALIZA DADOS")
  @ApiResponses({
    @ApiResponse(code = 403, message = "Funcionário não autorizado"),
    @ApiResponse(code = 404, message = "Produto não encontrado"),
    @ApiResponse(code = 200, message = "Produto atualizado com sucesso")
  })
  public ResponseEntity<Object> update(@PathVariable(name = "id") @ApiParam("ID DO CARRO") UUID id,
                                       @RequestBody @Valid ProdutoDTO produtoDTO) {

    Optional<ProdutoModel> produtoModelOptional = produtoService.getById(id);
    if (!produtoModelOptional.isPresent()) {
      ErrorMessages idNull = new ErrorMessages("ID NÃO ENCONTRADO!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(idNull.getMensagem());
    }

    ProdutoModel produtoModel = ClienteController.instanciarESetarPropriedades(produtoDTO, ProdutoModel.class);
    produtoModel.setId(produtoModelOptional.get().getId());
    return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoModel));

  }

}
