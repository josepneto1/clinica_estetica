package org.example.clinica_estetica;

import junit.framework.TestCase;
import org.checkerframework.checker.units.qual.A;
import org.example.clinica_estetica.controller.AcessoController;
import org.example.clinica_estetica.model.Acesso;
import org.example.clinica_estetica.repository.AcessoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ClinicaEsteticaApplication.class)
public class ClinicaEsteticaApplicationTests {

    @Autowired
    private AcessoController acessoController;

    @Autowired
    private AcessoRepository acessoRepository;

    @Test
    public void testCadastraAcesso() {

        Acesso acesso = new Acesso();

        acesso.setDescricao("ROLE_ADMIN");

        assertNull(acesso.getId());

        acesso = acessoController.salvarAcesso(acesso).getBody();

        assertTrue(acesso.getId() > 0);

        assertEquals("ROLE_ADMIN", acesso.getDescricao());

        // Teste de carregamento
        Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();

        assertEquals(acesso.getId(), acesso2.getId());

        // Teste de delete
        acessoRepository.deleteById(acesso2.getId());

        Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);

        assertNull(acesso3);

        // Teste de query
        acesso = new Acesso();

        acesso.setDescricao("ROLE_ALUNO");

        acesso = acessoController.salvarAcesso(acesso).getBody();

        List<Acesso> acessos = acessoRepository.buscarAcessoDescricao("ALUNO".trim().toUpperCase());

        assertEquals(1, acessos.size());

        acessoRepository.deleteById(acesso.getId());
    }

}
