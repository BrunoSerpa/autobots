package com.autobots.automanager.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.Atualizador;
import com.autobots.automanager.modelo.DocumentoValidador;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@Service
public class DocumentoServico {
    @Autowired
    private DocumentoRepositorio repositorio;

    public List<Documento> listarDocumentos() {
        return repositorio.findAll();
    }

    public Optional<Documento> buscarPorId(Long id) {
    	return repositorio.findById(id);
    }
}