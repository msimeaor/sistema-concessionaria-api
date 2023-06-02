package io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl;

import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.InfoItemPedidoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.InfoPedidoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.ItemPedidoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.dto.PedidoDTO;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ClienteModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ItemPedidoModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.PedidoModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.ProdutoModel;
import io.github.msimeaor.sistemaconcessionariaapi.domain.repository.ItemPedidoRepository;
import io.github.msimeaor.sistemaconcessionariaapi.domain.repository.PedidoRepository;
import io.github.msimeaor.sistemaconcessionariaapi.domain.service.PedidoService;
import io.github.msimeaor.sistemaconcessionariaapi.exceptions.ExceptionLancada;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

  private final PedidoRepository pedidoRepository;
  private final ClienteServiceImpl clienteService;
  private final ProdutoServiceImpl produtoService;
  private final ItemPedidoRepository itemPedidoRepository;

  @Transactional
  public PedidoModel save(PedidoDTO pedidoDTO) {
    Optional<ClienteModel> clienteModelOptional =
      clienteService.getById(pedidoDTO.getCliente());
    if (!clienteModelOptional.isPresent()) {
      throw new ExceptionLancada("CLIENTE NÃO ENCONTRADO!");
    }

    PedidoModel pedidoModel = PedidoModel.builder()
      .cliente(clienteModelOptional.get())
      .data(LocalDate.now())
      .total(calcularTotalPedido(pedidoDTO))
      .build();

    List<ItemPedidoModel> itensPedidos =
      converterItemPedidoDTOEmItemPedidoModel(pedidoModel, pedidoDTO);

    pedidoRepository.save(pedidoModel);
    itemPedidoRepository.saveAll(itensPedidos);
    pedidoModel.setItensPedidos(itensPedidos);

    return pedidoModel;
  }

  private BigDecimal calcularTotalPedido(PedidoDTO pedidoDTO) {
    BigDecimal valorTotal = BigDecimal.ZERO;

    for(ItemPedidoDTO itemPedidoDTO : pedidoDTO.getItensPedidos()) {
      Optional<ProdutoModel> produtoModelOptional =
        produtoService.getById(itemPedidoDTO.getProduto());
      if (!produtoModelOptional.isPresent()) {
        throw new ExceptionLancada("PRODUTO NÃO ENCONTRADO!");
      }

      ProdutoModel produtoModel = produtoModelOptional.get();

      BigDecimal totalItem = new BigDecimal(produtoModel.getPreco().longValue()
        * itemPedidoDTO.getQuantidade());
      valorTotal = valorTotal.add(totalItem);
    }

    return valorTotal;
  }

  private List<ItemPedidoModel> converterItemPedidoDTOEmItemPedidoModel(PedidoModel pedidoModel,
                                                                        PedidoDTO pedidoDTO) {

    List<ItemPedidoModel> itensPedidos = new ArrayList<>();

    for(ItemPedidoDTO itemPedidoDTO : pedidoDTO.getItensPedidos()) {
      Optional<ProdutoModel> produtoModelOptional = produtoService.getById(itemPedidoDTO.getProduto());

      if (!produtoModelOptional.isPresent()) {
        throw new ExceptionLancada("PRODUTO NÃO ENCONTRADO!");
      }

      ProdutoModel produtoModel = produtoModelOptional.get();

      if (itemPedidoDTO.getQuantidade() > produtoModel.getEstoque()) {
        throw new ExceptionLancada("ESTOQUE INSUFICIENTE!");
      }

      ItemPedidoModel itemPedidoModel = ItemPedidoModel.builder()
        .pedido(pedidoModel)
        .produto(produtoModelOptional.get())
        .quantidade(itemPedidoDTO.getQuantidade())
        .build();

      itensPedidos.add(itemPedidoModel);
      produtoModel.setEstoque(produtoModel.getEstoque() - itemPedidoDTO.getQuantidade());
    }

    return itensPedidos;
  }

  public InfoPedidoDTO getInfoPedidoDTO(PedidoModel pedidoModel) {
    return InfoPedidoDTO.builder()
      .pedido(pedidoModel.getId())
      .cliente(pedidoModel.getCliente().getId())
      .data(pedidoModel.getData())
      .total(pedidoModel.getTotal())
      .itensPedidos(converterItemPedidoModelEmInfoItemPedidoDTO(pedidoModel.getItensPedidos()))
      .build();
  }

  private List<InfoItemPedidoDTO> converterItemPedidoModelEmInfoItemPedidoDTO(
    List<ItemPedidoModel> itensPedidosModel) {

    List<InfoItemPedidoDTO> infoItemPedidoDTOS = new ArrayList<>();

    for(ItemPedidoModel item : itensPedidosModel) {
      InfoItemPedidoDTO infoItemPedidoDTO = InfoItemPedidoDTO.builder()
        .itemPedido(item.getId())
        .produto(item.getProduto().getId())
        .quantidade(item.getQuantidade())
        .build();

      infoItemPedidoDTOS.add(infoItemPedidoDTO);
    }

    return infoItemPedidoDTOS;
  }

}
