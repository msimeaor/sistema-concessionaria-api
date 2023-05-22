package io.github.msimeaor.sistemaconcessionariaapi.restcontroller;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.ProdutoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ProdutoModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.ProdutoServiceImpl;
import io.github.msimeaor.sistemaconcessionariaapi.exceptions.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/produto")
public class ProdutoController {

  private final ProdutoServiceImpl produtoService;

  @PostMapping
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
  public ResponseEntity<Object> getByChassi(@PathVariable(name = "chassi") String chassi) {
    Optional<ProdutoModel> produtoModelOptional = produtoService.getByChassi(chassi);
    if (!produtoModelOptional.isPresent()) {
      ErrorMessages produtoNull = new ErrorMessages("CHASSI NÃO CADASTRADO!");
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(produtoNull.getMensagem());
    }

    return ResponseEntity.status(HttpStatus.OK).body(produtoModelOptional.get());

  }

}
