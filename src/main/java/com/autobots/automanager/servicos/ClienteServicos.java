package com.autobots.automanager.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelo.Atualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@Service
public class ClienteServicos {
	@Autowired
	private ClienteRepositorio repositorio;
	
	public Cliente salvarCliente(Cliente cliente) {
		return repositorio.save(cliente);
	}
	
	public List<Cliente> listarClientes() {
		return repositorio.findAll();
	}
	
	public Optional<Cliente> buscarPorId(Long id) {
		return repositorio.findById(id);
	}
	
	public Cliente atualizarCliente(Long id, Cliente atualizacao) {
        return repositorio.findById(id).map(cliente -> {
        	Atualizador atualizador = new Atualizador();
        	atualizador.atualizarCliente(cliente, atualizacao);
            return repositorio.save(cliente);
        }).orElse(null);
    }

    public void excluirCliente(Long id) {
    	repositorio.deleteById(id);
    }
}