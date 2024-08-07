package com.autobots.automanager.modelo;

import java.util.List;

import com.autobots.automanager.entidades.*;

public class Atualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizarCliente(Cliente cliente, Cliente atualizacao){
        if (!verificador.verificar(atualizacao.getNome())) {
            cliente.setNome(atualizacao.getNome());
        }
        if (!verificador.verificar(atualizacao.getNomeSocial())) {
            cliente.setNomeSocial(atualizacao.getNomeSocial());
        }
        if (atualizacao.getDataCadastro() != null) {
            cliente.setDataCadastro(atualizacao.getDataCadastro());
        }
        if (atualizacao.getDataNascimento() != null) {
            cliente.setDataNascimento(atualizacao.getDataNascimento());
        }

        if (atualizacao.getEndereco() != null) {
            if (cliente.getEndereco() == null) {
                cliente.setEndereco(atualizacao.getEndereco());
            } else {
                atualizarEndereco(cliente.getEndereco(), atualizacao.getEndereco());
            }
        }
        if (atualizacao.getTelefones() != null) {
            if (cliente.getTelefones() == null) {
            	cliente.setTelefones(atualizacao.getTelefones());
            } else {            	
            	atualizarTelefones(cliente.getTelefones(), atualizacao.getTelefones());
            }
        }
        if (atualizacao.getDocumentos() != null) {
        	if (cliente.getDocumentos() == null) {
        		cliente.setDocumentos(atualizacao.getDocumentos());
        	} else {
        		atualizarDocumentos(cliente.getDocumentos(), atualizacao.getDocumentos());
        	}
        }
    }

    public void atualizarEndereco(Endereco endereco, Endereco atualizacao){
        if (!verificador.verificar(atualizacao.getEstado())) {
            endereco.setEstado(atualizacao.getEstado());
        }
        if (!verificador.verificar(atualizacao.getCidade())) {
            endereco.setCidade(atualizacao.getCidade());
        }
        if (!verificador.verificar(atualizacao.getBairro())) {
            endereco.setBairro(atualizacao.getBairro());
        }
        if (!verificador.verificar(atualizacao.getRua())) {
            endereco.setRua(atualizacao.getRua());
        }
        if (!verificador.verificar(atualizacao.getNumero())) {
            endereco.setNumero(atualizacao.getNumero());
        }
        if (!verificador.verificar(atualizacao.getInformacoesAdicionais())) {
            endereco.setInformacoesAdicionais(atualizacao.getInformacoesAdicionais());
        }
    }

    public void atualizarDocumentos(List<Documento> documentos, List<Documento> atualizacoes){
        for (Documento atualizacao : atualizacoes) {
            for (Documento documento : documentos) {
                if (atualizacao.getId() != null) {
                    if (atualizacao.getId().equals(documento.getId())) {
                        atualizarDocumento(documento, atualizacao);
                    }
                }
            }
        }
    }

    public void atualizarDocumento(Documento documento, Documento atualizacao){
        if (!verificador.verificar(atualizacao.getTipo())) {
            documento.setTipo(atualizacao.getTipo());
        }
        if (!verificador.verificar(atualizacao.getNumero())) {
            documento.setNumero(atualizacao.getNumero());
        }
    }

    public void atualizarTelefones(List<Telefone> telefones, List<Telefone> atualizacoes){
        if (atualizacoes != null && !atualizacoes.isEmpty()) {
            telefones.clear();
            telefones.addAll(atualizacoes);
        }
    }
}