package io.github.msimeaor.sistemaconcessionariaapi.domain.dto;

import io.github.msimeaor.sistemaconcessionariaapi.domain.enums.Uf;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Getter
@Setter
public class FornecedorDTO {

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  private String nome;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 18, message = "{model.message.size.limit}")
  @CNPJ(message = "{model.message.invalid.cnpj}")
  private String cnpj;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Email(message = "{model.message.invalid.email}")
  private String email;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 12, message = "{model.message.size.limit}")
  private String telefone;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 13, message = "{model.message.size.limit}")
  private String celular;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 9, message = "{model.message.size.limit}")
  private String cep;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 100, message = "{model.message.size.limit}")
  private String endereco;

  @NotNull(message = "{model.message.null}")
  @PositiveOrZero(message = "{model.message.negative.value}")
  private Integer numero;

  @Size(max = 100, message = "{model.message.size.limit}")
  private String complement;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  private String bairro;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  private String cidade;

  @NotEmpty(message = "{model.message.empty}")
  @Enumerated(EnumType.STRING)
  private Uf uf;

}
