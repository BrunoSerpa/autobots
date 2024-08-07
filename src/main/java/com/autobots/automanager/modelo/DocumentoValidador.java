package com.autobots.automanager.modelo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentoValidador {
	private StringVerificadorNulo verificador = new StringVerificadorNulo();
    private static final int LIMITE_TIPO = 180;
    private static final int LIMITE_NUMERO = 20;

    public List<String> validar(Documento documento) {
        List<String> erros = new ArrayList<>();

        String tipo = documento.getTipo();
        String numero = documento.getNumero();

        if (verificador.verificar(tipo)) {
        	erros.add("Tipo de documento não pode estar vazio.");        	
        } else if (tipo.length() > LIMITE_TIPO) {
        	erros.add("Tipo de documento deve ter no máximo " + LIMITE_TIPO + " caracteres.");
        }

        if (verificador.verificar(numero)) {
        	erros.add("Número de documento não pode estar vazio.");
        } else if (numero.length() > LIMITE_NUMERO) {
            erros.add("Número de documento deve ter no máximo " + LIMITE_NUMERO + " caracteres.");
        } else if (procuraIgual(numero)) {
            erros.add("Número de documento " + numero + " já foi cadastrado.");
    	}

        return erros;
    }

    @Autowired
    DocumentoRepositorio repositorio;
    private Boolean procuraIgual(String numero) {
    	if (repositorio == null) {
            return false;
        }
        return repositorio.existsByNumero(numero);
    }
}