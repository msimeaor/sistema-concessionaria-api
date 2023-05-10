package io.github.msimeaor.sistemaconcessionariaapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProdutoDTO {

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 100, message = "{model.message.size.limit}")
  private String descricao;

  @NotNull(message = "{model.message.null}")
  @Digits(integer = 20, fraction = 2, message = "{model.message.invalid.price}")
  @PositiveOrZero(message = "{model.message.negative.value}")
  private BigDecimal preco;

  @NotNull(message = "{model.message.null}")
  private UUID fornecedor;

}
