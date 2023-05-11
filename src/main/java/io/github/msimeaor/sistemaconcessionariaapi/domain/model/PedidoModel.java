package io.github.msimeaor.sistemaconcessionariaapi.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.msimeaor.sistemaconcessionariaapi.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PEDIDO")
public class PedidoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private UUID id;

  @JsonIgnore
  @NotNull(message = "{model.message.null}")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "CLIENTE")
  private ClienteModel clienteModel;

  @NotNull(message = "{model.message.null}")
  @FutureOrPresent(message = "{model.message.invalid.date}")
  @Column(name = "DATA", nullable = false)
  private LocalDate data;

  @NotNull(message = "{model.message.null}")
  @Digits(integer = 20, fraction = 2)
  @Column(name = "TOTAL", nullable = false, precision = 20, scale = 2)
  private BigDecimal total;

  @OneToMany(mappedBy = "pedidoModel")
  @NotEmptyList(message = "{model.message.empty.list}")
  private List<ItemPedidoModel> itensPedidos;

}
