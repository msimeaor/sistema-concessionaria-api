package io.github.msimeaor.sistemaconcessionariaapi.security.jwt;

import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.FuncionarioServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

  private JwtService jwtService;
  private FuncionarioServiceImpl funcionarioService;

  public JwtAuthFilter(JwtService jwtService, FuncionarioServiceImpl funcionarioService) {
    this.jwtService = jwtService;
    this.funcionarioService = funcionarioService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    String authorization = request.getHeader("Authorization");

    if (authorization != null && authorization.startsWith("Bearer")) {
      String token = authorization.split(" ")[1];
      boolean tokenValido = jwtService.tokenIsValid(token);

      if (tokenValido) {
        String userName = jwtService.getEmail(token);
        UserDetails funcionario = funcionarioService.loadUserByUsername(userName);

        UsernamePasswordAuthenticationToken user =
          new UsernamePasswordAuthenticationToken(funcionario, null, funcionario.getAuthorities());

        user.setDetails(new WebAuthenticationDetailsSource()
          .buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(user);
      }

    }

    filterChain.doFilter(request, response);

  }

}
