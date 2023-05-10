package io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl;

import io.github.msimeaor.sistemaconcessionariaapi.domain.repository.ItemPedidoRepository;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.ItemPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ItemPedidoServiceImpl implements ItemPedidoService {

  private final ItemPedidoRepository itemPedidoRepository;

}
