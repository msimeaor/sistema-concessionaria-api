package io.github.msimeaor.sistemaconcessionariaapi.domain.dto;

import io.github.msimeaor.sistemaconcessionariaapi.validation.NotEmptyList;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PedidoDTO {

  @NotNull(message = "{model.message.null}")
  private UUID clienteModel;

  @OneToMany(mappedBy = "pedidoModel")
  @NotEmptyList(message = "{model.message.empty.list}")
  private List<ItemPedidoDTO> itensPedidos;

}
