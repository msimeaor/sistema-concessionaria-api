package io.github.msimeaor.sistemaconcessionariaapi.configurations;

import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.FuncionarioServiceImpl;
import io.github.msimeaor.sistemaconcessionariaapi.security.jwt.JwtAuthFilter;
import io.github.msimeaor.sistemaconcessionariaapi.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private FuncionarioServiceImpl funcionarioService;

  @Autowired
  private JwtService jwtService;

  @Bean
  public OncePerRequestFilter jwtFilter() {
    return new JwtAuthFilter(jwtService, funcionarioService);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .userDetailsService(funcionarioService)
      .passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http
      .csrf().disable()
        .authorizeRequests()
          .antMatchers("/cliente/**")
            .hasAnyRole("USER", "ADMIN")
          .antMatchers("/produto/**")
            .hasRole("ADMIN")
          .antMatchers("/pedido/**")
            .hasAnyRole("USER", "ADMIN")
          .antMatchers("/funcionario/**")
            .permitAll()
      .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
        .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

  }

}
