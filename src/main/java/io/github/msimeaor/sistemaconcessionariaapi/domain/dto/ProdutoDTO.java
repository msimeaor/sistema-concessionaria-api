package io.github.msimeaor.sistemaconcessionariaapi.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class ProdutoDTO {

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  private String marca;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  private String modelo;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 4, message = "{model.message.size.limit}")
  private String ano;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  private String cor;

  @Size(max = 7, message = "{model.message.size.limit}")
  private String placa;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 17, message = "{model.message.size.limit}")
  private String chassi;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 3, message = "{model.message.size.limit}")
  private String motor;

  @NotNull(message = "{model.message.null}")
  @Digits(integer = 20, fraction = 2, message = "{model.message.invalid.price}")
  @PositiveOrZero(message = "{model.message.negative.value}")
  private BigDecimal preco;

}
