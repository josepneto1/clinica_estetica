package org.example.clinica_estetica;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.clinica_estetica.controller.AcessoController;
import org.example.clinica_estetica.model.Acesso;
import org.example.clinica_estetica.repository.AcessoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ClinicaEsteticaApplication.class)
public class ClinicaEsteticaApplicationTests {

    @Autowired
    private AcessoController acessoController;

    @Autowired
    private AcessoRepository acessoRepository;

    @Autowired
    private WebApplicationContext wac;

    /*Teste do endpoint de salvar*/
    @Test
    public void testRestApiCadastroAcesso() throws Exception {

        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();

        acesso.setDescricao("ROLE_FUNCIONARIO");

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.post("/salvarAcesso")
                .content(objectMapper.writeValueAsString(acesso))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        System.out.println("Retorno api" + retornoApi.andReturn().getResponse().getContentAsString());
        Acesso objetoRetorno = objectMapper
                .readValue(retornoApi.andReturn().getResponse().getContentAsString(),
                        Acesso.class);

        assertEquals(acesso.getDescricao(), objetoRetorno.getDescricao());
    }

    @Test
    public void testRestApiDeleteAcesso() throws Exception {

        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();

        acesso.setDescricao("ROLE_TESTE_DELETE");

        acesso = acessoRepository.save(acesso);

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.delete("/deletarAcesso")
                .content(objectMapper.writeValueAsString(acesso))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        System.out.println("Retorno api: " + retornoApi.andReturn().getResponse().getContentAsString());
        System.out.println("status de retorno: " + retornoApi.andReturn().getResponse().getStatus());

        assertEquals("Acesso removido", retornoApi.andReturn().getResponse().getContentAsString());
        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
    }

    @Test
    public void testRestApiDeletePorIdAcesso() throws Exception {

        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();

        acesso.setDescricao("ROLE_TESTE_DELETE_ID");

        acesso = acessoRepository.save(acesso);

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.delete(
                        "/deletarAcessoPorId/" + acesso.getId())
                .content(objectMapper.writeValueAsString(acesso))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        System.out.println("Retorno api: " + retornoApi.andReturn().getResponse().getContentAsString());
        System.out.println("status de retorno: " + retornoApi.andReturn().getResponse().getStatus());

        assertEquals("Acesso removido por id", retornoApi.andReturn().getResponse().getContentAsString());
        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());
    }

    @Test
    public void testRestApiObterAcessoId() throws Exception {

        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();

        acesso.setDescricao("ROLE_TESTE_OBTER_ID");

        acesso = acessoRepository.save(acesso);

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get(
                        "/obterAcesso/" + acesso.getId())
                .content(objectMapper.writeValueAsString(acesso))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

        Acesso acessoRetorno = objectMapper.readValue(retornoApi.andReturn().getResponse().getContentAsString(), Acesso.class);

        assertEquals(acesso.getDescricao(), acessoRetorno.getDescricao());

        assertEquals(acesso.getId(), acessoRetorno.getId());
    }

    @Test
    public void testRestApiObterAcessoDesc() throws Exception {

        DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
        MockMvc mockMvc = builder.build();

        Acesso acesso = new Acesso();

        acesso.setDescricao("ROLE_TESTE_OBTER_LIST");

        acesso = acessoRepository.save(acesso);

        ObjectMapper objectMapper = new ObjectMapper();

        ResultActions retornoApi = mockMvc.perform(MockMvcRequestBuilders.get("/buscarPorDescricao/OBTER_LIST")
                .content(objectMapper.writeValueAsString(acesso))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON));

        assertEquals(200, retornoApi.andReturn().getResponse().getStatus());

        List<Acesso> retornoApiList = objectMapper.readValue(
                retornoApi.andReturn().getResponse().getContentAsString(),
                new TypeReference<List<Acesso>>() {});

        assertEquals(1, retornoApiList.size());

        assertEquals(acesso.getDescricao(), retornoApiList.get(0).getDescricao());

        acessoRepository.deleteById(acesso.getId());
    }

//    @Test
//    public void testCadastraAcesso() {
//
//        Acesso acesso = new Acesso();
//
//        acesso.setDescricao("ROLE_ADMIN");
//
//        assertNull(acesso.getId());
//
//        acesso = acessoController.salvarAcesso(acesso).getBody();
//
//        assertTrue(acesso.getId() > 0);
//
//        assertEquals("ROLE_ADMIN", acesso.getDescricao());
//
//        // Teste de carregamento
//        Acesso acesso2 = acessoRepository.findById(acesso.getId()).get();
//
//        assertEquals(acesso.getId(), acesso2.getId());
//
//        // Teste de delete
//        acessoRepository.deleteById(acesso2.getId());
//
//        Acesso acesso3 = acessoRepository.findById(acesso2.getId()).orElse(null);
//
//        assertNull(acesso3);
//
//        // Teste de query
//        acesso = new Acesso();
//
//        acesso.setDescricao("ROLE_ALUNO");
//
//        acesso = acessoController.salvarAcesso(acesso).getBody();
//
//        List<Acesso> acessos = acessoRepository.buscarAcessoDescricao("ALUNO".trim().toUpperCase());
//
//        assertEquals(1, acessos.size());
//
//        acessoRepository.deleteById(acesso.getId());
//    }

}
