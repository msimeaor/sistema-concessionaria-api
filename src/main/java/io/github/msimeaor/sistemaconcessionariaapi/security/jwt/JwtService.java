package io.github.msimeaor.sistemaconcessionariaapi.security.jwt;

import io.github.msimeaor.sistemaconcessionariaapi.SistemaConcessionariaApiApplication;
import io.github.msimeaor.sistemaconcessionariaapi.domain.model.FuncionarioModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {

  @Value("${security.jwt.expiracao}")
  private String expiracao;

  @Value("${security.jwt.chave-assinatura}")
  private String chaveAssinatura;

  public String gerarToken(FuncionarioModel funcionarioModel) {
    long expString = Long.valueOf(expiracao);
    LocalDateTime ldtExp = LocalDateTime.now().plusMinutes(expString);
    Date horaExpiracao = Date.from(ldtExp.atZone(ZoneId.systemDefault()).toInstant());

    return Jwts.builder()
      .setSubject(funcionarioModel.getEmail())
      .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
      .setExpiration(horaExpiracao)
      .compact();
  }

  private Claims obterClaims(String token) throws ExpiredJwtException {
    return Jwts
      .parser()
      .setSigningKey(chaveAssinatura)
      .parseClaimsJws(token)
      .getBody();
  }

  public boolean tokenIsValid(String token) {
    try {

      Date dtExpiracao = obterClaims(token).getExpiration();
      LocalDateTime ldtExpiracao =
        dtExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

      return LocalDateTime.now().isBefore(ldtExpiracao);

    } catch(Exception e) {
      return false;
    }
  }

  public String getEmail(String token) throws ExpiredJwtException {
    return (String) obterClaims(token).getSubject();
  }

  /*
  public static void main(String[] args) {
    ConfigurableApplicationContext context = SpringApplication.run(SistemaConcessionariaApiApplication.class);
    JwtService jwtService = context.getBean(JwtService.class);
    FuncionarioModel funcionarioModel = FuncionarioModel.builder().email("matheussimeao@gmail.com").cpf("081.838.201-58").nome("MATHEUS AUGUSTO").build();
    String token = jwtService.gerarToken(funcionarioModel);
    System.out.println(jwtService.getEmail(token));
  }
  */

}
