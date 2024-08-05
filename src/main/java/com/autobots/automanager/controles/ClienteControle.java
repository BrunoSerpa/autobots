package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.servicos.ClienteServicos;

@RestController
@RequestMapping("/clientes")
public class ClienteControle {
	@Autowired
	private ClienteServicos servicos;
	
	@GetMapping
	public List<Cliente> obterClientes() {
		return servicos.listarClientes();
	}

	@GetMapping("/{id}")
	public Optional<Cliente> obterCliente(@PathVariable long id) {
		return servicos.buscarPorId(id);
	}

	@PostMapping
	public ResponseEntity<Cliente> cadastrarCliente(@RequestBody Cliente cliente) {
		Cliente clienteSalvo = servicos.salvarCliente(cliente);
        return ResponseEntity.ok(clienteSalvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable long id, @RequestBody Cliente atualizacao) {
		Cliente clienteAtualizado = servicos.atualizarCliente(id, atualizacao);
		return clienteAtualizado != null ? ResponseEntity.ok(clienteAtualizado) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable long id) {
		servicos.excluirCliente(id);
        return ResponseEntity.noContent().build();
	}
}