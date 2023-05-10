package io.github.msimeaor.sistemaconcessionariaapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

@Getter
@Setter
public class ItemPedidoDTO {

  @NotNull(message = "{model.message.null}")
  private UUID produtoModel;

  @NotNull(message = "{model.message.null}")
  @PositiveOrZero(message = "{model.message.negative.value}")
  private Integer quantidade;

}
