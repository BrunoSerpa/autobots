package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.servicos.TelefoneServicos;

@RestController
@RequestMapping("/telefones")
public class TelefoneControle {
    @Autowired
    private TelefoneServicos servicos;

    @GetMapping
    public List<Telefone> obterDocumentos() {
        return servicos.listarTelefones();
    }

    @GetMapping("/{id}")
    public Optional<Telefone> obterDocumento(@PathVariable Long id) {
        return servicos.buscarPorId(id);
    }

}
