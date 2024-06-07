package org.example.clinica_estetica.security;

import jakarta.servlet.http.HttpSessionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebConfigSecurity implements HttpSessionListener {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(HttpMethod.GET, "/salvarAcesso")
                .requestMatchers(HttpMethod.GET, "/obterAcesso/{id}")
                .requestMatchers(HttpMethod.POST, "/salvarAcesso")
                .requestMatchers(HttpMethod.DELETE, "/deletarAcesso");
    }
}
