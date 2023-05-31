package io.github.msimeaor.sistemaconcessionariaapi.domain.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoPedidoDTO {

  public UUID pedido;
  public UUID cliente;
  public LocalDate data;
  public BigDecimal total;
  public List<InfoItemPedidoDTO> itensPedidos;

}
