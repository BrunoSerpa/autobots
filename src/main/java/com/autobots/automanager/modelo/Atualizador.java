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
		if (!(atualizacao.getDataCadastro() == null)) {
			cliente.setDataCadastro(atualizacao.getDataCadastro());
		}
		if (!(atualizacao.getDataNascimento() == null)) {
			cliente.setDataNascimento(atualizacao.getDataNascimento());
		}
		atualizarEndereco(cliente.getEndereco(), atualizacao.getEndereco());
		atualizarDocumentos(cliente.getDocumentos(), atualizacao.getDocumentos());
		atualizarTelefones(cliente.getTelefones(), atualizacao.getTelefones());
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
					if (atualizacao.getId() == documento.getId()) {
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
		for (Telefone atualizacao : atualizacoes) {
			for (Telefone telefone : telefones) {
				if (atualizacao.getId() != null) {
					if (atualizacao.getId() == telefone.getId()) {
						atualizarTelefone(telefone, atualizacao);
					}
				}
			}
		}
	}

	public void atualizarTelefone(Telefone telefone, Telefone atualizacao){
		if (!verificador.verificar(atualizacao.getDdd())) {
			telefone.setDdd(atualizacao.getDdd());
		}
		if (!verificador.verificar(atualizacao.getNumero())) {
			telefone.setNumero(atualizacao.getNumero());
		}
	}
}