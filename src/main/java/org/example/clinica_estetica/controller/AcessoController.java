package org.example.clinica_estetica.controller;

import org.example.clinica_estetica.model.Acesso;
import org.example.clinica_estetica.repository.AcessoRepository;
import org.example.clinica_estetica.service.AcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
public class AcessoController {

    @Autowired
    private AcessoService acessoService;

    @Autowired
    private AcessoRepository acessoRepository;

    @ResponseBody
    @PostMapping(value = "/salvarAcesso")
    public ResponseEntity<Acesso> salvarAcesso(@RequestBody Acesso acesso) {

        Acesso acessoSalvo = acessoService.save(acesso);

        return new ResponseEntity<Acesso>(acessoSalvo, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "/deletarAcesso")
    public ResponseEntity<?> deletarAcesso(@RequestBody Acesso acesso) {

        acessoRepository.deleteById(acesso.getId());

        return new ResponseEntity("Acesso removido", HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "/deletarAcessoPorId/{id}")
    public ResponseEntity<?> deletarAcessoPorId(@PathVariable("id") Long id) {

        acessoRepository.deleteById(id);

        return new ResponseEntity("Acesso removido por id", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/obterAcesso/{id}")
    public ResponseEntity<Acesso> obterAcesso(@PathVariable("id") Long id) {

        Acesso acesso = acessoRepository.findById(id).get();

        return new ResponseEntity<Acesso>(acesso, HttpStatus.OK);
    }
    @ResponseBody
    @GetMapping(value = "/buscarPorDescricao/{descricao}")
    public ResponseEntity<List<Acesso>> buscarPorDescricao(@PathVariable("descricao") String descricao) {

        List<Acesso> acesso = acessoRepository.buscarAcessoDescricao(descricao);

        return new ResponseEntity<List<Acesso>>(acesso, HttpStatus.OK);
    }
}
