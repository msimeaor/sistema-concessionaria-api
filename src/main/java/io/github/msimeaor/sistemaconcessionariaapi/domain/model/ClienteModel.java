package io.github.msimeaor.sistemaconcessionariaapi.domain.model;

import io.github.msimeaor.sistemaconcessionariaapi.domain.enums.Uf;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CLIENTE")
public class ClienteModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "ID")
  private UUID id;

  @Column(name = "NOME", nullable = false, length = 50)
  private String nome;

  @Column(name = "RG", nullable = false, unique = true, length = 9)
  private String rg;

  @Column(name = "CPF", nullable = false, unique = true, length = 14)
  private String cpf;

  @Column(name = "EMAIL", nullable = false, unique = true, length = 50)
  private String email;

  @Column(name = "TELEFONE", nullable = false, length = 12)
  private String telefone;

  @Column(name = "CELULAR", nullable = false, length = 13)
  private String celular;

  @Column(name = "CEP", nullable = false, length = 9)
  private String cep;

  @Column(name = "ENDERECO", nullable = false, length = 100)
  private String endereco;

  @Column(name = "NUMERO", nullable = false)
  private Integer numero;

  @Column(name = "COMPLEMENTO", length = 100)
  private String complemento;

  @Column(name = "BAIRRO", nullable = false, length = 50)
  private String bairro;

  @Column(name = "CIDADE", nullable = false, length = 50)
  private String cidade;

  @Column(name = "UF", nullable = false)
  @Enumerated(EnumType.STRING)
  private Uf uf;

}
