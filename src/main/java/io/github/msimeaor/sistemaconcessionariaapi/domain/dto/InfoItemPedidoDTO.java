package io.github.msimeaor.sistemaconcessionariaapi.domain.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InfoItemPedidoDTO {

  public UUID itemPedido;
  public UUID produto;
  public Integer quantidade;

}
