package io.github.msimeaor.sistemaconcessionariaapi.domain.model;

import io.github.msimeaor.sistemaconcessionariaapi.domain.enums.Uf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "FORNECEDOR")
public class FornecedorModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private UUID id;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Column(name = "NOME", nullable = false, length = 50)
  private String nome;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 18, message = "{model.message.size.limit}")
  @CNPJ(message = "{model.message.invalid.cnpj}")
  @Column(name = "CNPJ", nullable = false, unique = true, length = 18)
  private String cnpj;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Email(message = "{model.message.invalid.email}")
  @Column(name = "EMAIL", nullable = false, unique = true, length = 50)
  private String email;

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
  private String complement;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Column(name = "BAIRRO", nullable = false, length = 50)
  private String bairro;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Column(name = "CIDADE", nullable = false, length = 50)
  private String cidade;

  @NotEmpty(message = "{model.message.empty}")
  @Enumerated(EnumType.STRING)
  @Column(name = "UF", nullable = false)
  private Uf uf;

  @OneToMany(mappedBy = "fornecedorModel")
  private List<ProdutoModel> listaProdutos;

}
