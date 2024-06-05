package org.example.clinica_estetica.service;

import org.example.clinica_estetica.model.Acesso;
import org.example.clinica_estetica.repository.AcessoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {

    @Autowired
    private AcessoRepository acessoRepository;

    public Acesso save(Acesso acesso) {

        return acessoRepository.save(acesso);
    }
}
