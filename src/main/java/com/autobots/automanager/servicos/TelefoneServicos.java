package com.autobots.automanager.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@Service
public class TelefoneServicos {
	@Autowired
	private TelefoneRepositorio repositorio;

	public List<Telefone> listarTelefones() {
		return repositorio.findAll();
	}
	
	public Optional<Telefone> buscarPorId(Long id) {
		return repositorio.findById(id);
	}
}
