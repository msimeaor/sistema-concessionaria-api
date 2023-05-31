package io.github.msimeaor.sistemaconcessionariaapi.domain.service;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.InfoPedidoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.PedidoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.PedidoModel;

public interface PedidoService {

  PedidoModel save(PedidoDTO pedidoDTO);
  InfoPedidoDTO getInfoPedidoDTO(PedidoModel pedidoModel);

}
