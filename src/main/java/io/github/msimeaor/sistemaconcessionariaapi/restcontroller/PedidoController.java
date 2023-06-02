package io.github.msimeaor.sistemaconcessionariaapi.restcontroller;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.InfoPedidoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.PedidoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.PedidoModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.PedidoServiceImpl;
import io.github.msimeaor.sistemaconcessionariaapi.exceptions.ExceptionLancada;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pedido")
@RestController
public class PedidoController {

  private final PedidoServiceImpl pedidoService;

  @PostMapping
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
