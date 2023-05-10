package io.github.msimeaor.sistemaconcessionariaapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PRODUTO")
public class ProdutoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private UUID id;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 100, message = "{model.message.size.limit}")
  @Column(name = "DESCRICAO", nullable = false, length = 100)
  private String descricao;

  @NotNull(message = "{model.message.null}")
  @Digits(integer = 20, fraction = 2, message = "{model.message.invalid.price}")
  @PositiveOrZero(message = "{model.message.negative.value}")
  @Column(name = "PRECO", nullable = false, precision = 20, scale = 2)
  private BigDecimal preco;

  @NotNull(message = "{model.message.null}")
  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "FORNECEDOR")
  private FornecedorModel fornecedorModel;

}
