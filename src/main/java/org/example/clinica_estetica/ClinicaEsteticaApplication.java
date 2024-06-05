package org.example.clinica_estetica;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "org.example.clinica_estetica.model")
@ComponentScan(basePackages = {"org.example.clinica_estetica.*"})
@EnableJpaRepositories(basePackages = {"org.example.clinica_estetica.repository"})
@EnableTransactionManagement
public class ClinicaEsteticaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicaEsteticaApplication.class, args);
    }

}
