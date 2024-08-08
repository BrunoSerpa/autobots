package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.servicos.DocumentoServico;

@RestController
@RequestMapping("/documentos")
public class DocumentoControle {
    @Autowired
    private DocumentoServico servicos;

    @GetMapping
    public List<Documento> obterDocumentos() {
        return servicos.listarDocumentos();
    }

    @GetMapping("/{id}")
    public Optional<Documento> obterDocumento(@PathVariable Long id) {
        return servicos.buscarPorId(id);
    }
}