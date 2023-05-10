package io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl;

import io.github.msimeaor.sistemaconcessionariaapi.domain.repository.PedidoRepository;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

  private final PedidoRepository pedidoRepository;

}
