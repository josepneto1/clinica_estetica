package org.example.clinica_estetica.security;

import jakarta.servlet.http.HttpSessionListener;
import org.example.clinica_estetica.service.ImplementacaoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebConfigSecurity implements HttpSessionListener {

    @Autowired
    private ImplementacaoUserDetailsService implementacaoUserDetailsService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(implementacaoUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers(HttpMethod.GET, "/salvarAcesso").permitAll()
                        .requestMatchers(HttpMethod.GET, "/obterAcesso/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/salvarAcesso").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/deletarAcesso").permitAll()
                        .anyRequest().authenticated())
                .formLogin(withDefaults())
                .logout(logout -> logout.permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }
}
