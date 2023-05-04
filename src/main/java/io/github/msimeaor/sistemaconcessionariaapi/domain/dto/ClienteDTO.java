package io.github.msimeaor.sistemaconcessionariaapi.domain.dto;

import io.github.msimeaor.sistemaconcessionariaapi.domain.enums.Uf;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;

@Getter
@Setter
public class ClienteDTO {

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  private String nome;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 9, message = "{model.message.size.limit}")
  private String rg;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 14, message = "{model.message.size.limit}")
  @CPF(message = "{model.message.invalid.cpf}")
  private String cpf;

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
  private String complemento;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  private String bairro;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  private String cidade;

  @NotNull(message = "{model.message.null}")
  private Uf uf;

}
