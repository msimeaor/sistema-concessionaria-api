package io.github.msimeaor.sistemaconcessionariaapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.UUID;

@Getter
@Setter
public class ItemPedidoDTO {

  @NotNull(message = "{model.message.null}")
  private UUID produto;

  @NotNull(message = "{model.message.null}")
  @Positive(message = "{model.message.negative.value}")
  @Min(1)
  private Integer quantidade;

}
