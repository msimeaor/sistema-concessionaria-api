package io.github.msimeaor.sistemaconcessionariaapi.restcontroller;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.InfoPedidoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.PedidoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.PedidoModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.PedidoServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/pedido")
@RestController
public class PedidoController {

  private final PedidoServiceImpl pedidoService;

  @PostMapping
  public InfoPedidoDTO save(@RequestBody @Valid PedidoDTO pedidoDTO) {
    PedidoModel pedidoModel = pedidoService.save(pedidoDTO);
    InfoPedidoDTO infoPedidoDTO = pedidoService.getInfoPedidoDTO(pedidoModel);
    return infoPedidoDTO;
  }

}
