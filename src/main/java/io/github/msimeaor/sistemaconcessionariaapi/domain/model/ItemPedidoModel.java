package io.github.msimeaor.sistemaconcessionariaapi.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ITEM_PEDIDO")
public class ItemPedidoModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private UUID id;

  @NotNull(message = "{model.message.null}")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PEDIDO")
  private PedidoModel pedidoModel;

  @NotNull(message = "{model.message.null}")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PRODUTO")
  private ProdutoModel produtoModel;

  @NotNull(message = "{model.message.null}")
  @PositiveOrZero(message = "{model.message.negative.value}")
  @Column(name = "QUANTIDADE", nullable = false)
  private Integer quantidade;

}
