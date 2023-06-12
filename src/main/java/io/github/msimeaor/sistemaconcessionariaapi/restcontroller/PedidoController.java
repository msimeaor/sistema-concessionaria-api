package io.github.msimeaor.sistemaconcessionariaapi.restcontroller;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.InfoPedidoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.PedidoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.PedidoModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.PedidoServiceImpl;
import io.github.msimeaor.sistemaconcessionariaapi.exceptions.ExceptionLancada;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pedido")
@RestController
@Api("API DE PEDIDOS")
public class PedidoController {

  private final PedidoServiceImpl pedidoService;


  @PostMapping
  @ApiOperation("SALVA NO BANCO DE DADOS")
  @ApiResponses({
    @ApiResponse(code = 403, message = "Funcionário não autorizado"),
    @ApiResponse(code = 201, message = "Pedido criado com sucesso"),
    @ApiResponse(code = 400, message = "Erro de validação")
  })
  public ResponseEntity<Object> save(@RequestBody @Valid PedidoDTO pedidoDTO) {

    try {

      PedidoModel pedidoModel = pedidoService.save(pedidoDTO);
      InfoPedidoDTO infoPedidoDTO = pedidoService.getInfoPedidoDTO(pedidoModel);
      return ResponseEntity.status(HttpStatus.CREATED).body(infoPedidoDTO.getPedido());

    } catch (ExceptionLancada el) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(el.getMessage());
    }

  }

}
