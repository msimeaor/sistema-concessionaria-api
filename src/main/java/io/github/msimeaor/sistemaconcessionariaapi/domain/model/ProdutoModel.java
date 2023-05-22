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
  @Size(max = 50, message = "{model.message.size.limit}")
  @Column(name = "MARCA", nullable = false, length = 50)
  private String marca;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Column(name = "MODELO", nullable = false, length = 50)
  private String modelo;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 4, message = "{model.message.size.limit}")
  @Column(name = "ANO", nullable = false, length = 4)
  private String ano;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Column(name = "COR", nullable = false, length = 50)
  private String cor;

  @Size(max = 7, message = "{model.message.size.limit}")
  @Column(name = "PLACA", length = 7)
  private String placa;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 17, message = "{model.message.size.limit}")
  @Column(name = "CHASSI", nullable = false, unique = true, length = 17)
  private String chassi;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 3, message = "{model.message.size.limit}")
  @Column(name = "MOTOR", nullable = false, length = 3)
  private String motor;

  @NotEmpty(message = "{model.message.empty}")
  @Size(max = 50, message = "{model.message.size.limit}")
  @Column(name = "KILOMETRAGEM", nullable = false, length = 50)
  private String kilometragem;

  @NotNull(message = "{model.message.null}")
  @Column(name = "ESTOQUE", nullable = false)
  private Integer estoque;

  @NotNull(message = "{model.message.null}")
  @Digits(integer = 20, fraction = 2, message = "{model.message.invalid.price}")
  @PositiveOrZero(message = "{model.message.negative.value}")
  @Column(name = "PRECO", nullable = false, precision = 20, scale = 2)
  private BigDecimal preco;

}
