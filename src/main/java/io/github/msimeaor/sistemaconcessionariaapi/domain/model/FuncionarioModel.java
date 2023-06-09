package io.github.msimeaor.sistemaconcessionariaapi.domain.model;

import io.github.msimeaor.sistemaconcessionariaapi.domain.enums.Uf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "FUNCIONARIO")
public class FuncionarioModel {

  // PROVAVELMENTE ESTA CLASSE SERÁ ALTERADA CONFORME
  // CONHECIMENTO DE SPRINGSECURITY AVANÇAR..

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private UUID id;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Column(name = "NOME", nullable = false, length = 50)
  private String nome;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 9, message = "{model.message.size.limit}")
  @Column(name = "RG", nullable = false, unique = true, length = 9)
  private String rg;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 14, message = "{model.message.size.limit}")
  @CPF(message = "{model.message.invalid.cpf}")
  @Column(name = "CPF", nullable = false, unique = true, length = 14)
  private String cpf;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Email(message = "{model.message.invalid.email}")
  @Column(name = "EMAIL", nullable = false, unique = true, length = 50)
  private String email;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 100, message = "{model.message.size.limit}")
  @Column(name = "SENHA", nullable = false, length = 100)
  private String senha;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Column(name = "CARGO", nullable = false, length = 50)
  private String cargo;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Column(name = "ACESSO", nullable = false, length = 50)
  private String acesso;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 12, message = "{model.message.size.limit}")
  @Column(name = "TELEFONE", nullable = false, length = 12)
  private String telefone;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 13, message = "{model.message.size.limit}")
  @Column(name = "CELULAR", nullable = false, length = 13)
  private String celular;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 9, message = "{model.message.size.limit}")
  @Column(name = "CEP", nullable = false, length = 9)
  private String cep;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 100, message = "{model.message.size.limit}")
  @Column(name = "ENDERECO", nullable = false, length = 100)
  private String endereco;

  @NotNull(message = "{model.message.null}")
  @PositiveOrZero(message = "{model.message.negative.value}")
  @Column(name = "NUMERO", nullable = false)
  private Integer numero;

  @Size(max = 100, message = "{model.message.size.limit}")
  @Column(name = "COMPLEMENTO", length = 100)
  private String complemento;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Column(name = "BAIRRO", nullable = false, length = 50)
  private String bairro;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Column(name = "CIDADE", nullable = false, length = 50)
  private String cidade;

  @NotNull(message = "{model.message.null}")
  @Enumerated(EnumType.STRING)
  @Column(name = "UF", nullable = false)
  private Uf uf;

}
