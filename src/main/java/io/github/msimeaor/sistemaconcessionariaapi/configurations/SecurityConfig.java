package io.github.msimeaor.sistemaconcessionariaapi.configurations;

import io.github.msimeaor.sistemaconcessionariaapi.domain.service.impl.FuncionarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private FuncionarioServiceImpl funcionarioService;


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
        .httpBasic();

  }

}
