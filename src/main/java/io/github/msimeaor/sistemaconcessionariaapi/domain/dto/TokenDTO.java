package io.github.msimeaor.sistemaconcessionariaapi.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO {

  private String userName;
  private String token;

}
