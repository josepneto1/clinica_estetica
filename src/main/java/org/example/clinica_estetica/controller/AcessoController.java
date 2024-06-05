package org.example.clinica_estetica.controller;

import org.example.clinica_estetica.model.Acesso;
import org.example.clinica_estetica.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @PostMapping("/salvarAcesso")
    public Acesso salvarAcesso(Acesso acesso) {

        return acessoService.save(acesso);
    }
}
