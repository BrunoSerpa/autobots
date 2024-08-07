package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.servicos.EnderecoServicos;

@RestController
@RequestMapping("/enderecos")
public class EnderecoControle {
    @Autowired
    private EnderecoServicos servicos;

    @GetMapping
    public List<Endereco> obterEnderecos() {
        return servicos.listarEnderecos();
    }

    @GetMapping("/{id}")
    public Optional<Endereco> obterEndereco(@PathVariable long id) {
        return servicos.buscarPorId(id);
    }
}