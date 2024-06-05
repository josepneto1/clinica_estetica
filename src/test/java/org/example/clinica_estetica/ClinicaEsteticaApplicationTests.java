package org.example.clinica_estetica;

import org.example.clinica_estetica.controller.AcessoController;
import org.example.clinica_estetica.model.Acesso;
import org.example.clinica_estetica.repository.AcessoRepository;
import org.example.clinica_estetica.service.AcessoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ClinicaEsteticaApplication.class)
class ClinicaEsteticaApplicationTests {

//    @Autowired
//    private AcessoService acessoService;

//    @Autowired
//    private AcessoRepository acessoRepository;

    @Autowired
    private AcessoController acessoController;

    @Test
    void testCadastraAcesso() {

        Acesso acesso = new Acesso();

        acesso.setDescricao("ROLE_ADMIN");

        acessoController.salvarAcesso(acesso);
    }

}
