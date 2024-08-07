package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelo.ClienteValidador;
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
	public ResponseEntity<?> cadastrarCliente(@RequestBody Cliente cliente) {
	    ClienteValidador validador = new ClienteValidador();
	    List<String> erros = validador.validar(cliente);

	    if (!erros.isEmpty()) {
	        return ResponseEntity.badRequest().body(erros);
	    }

	    Cliente clienteSalvo = servicos.salvarCliente(cliente);
	    return ResponseEntity.ok(clienteSalvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarCliente(@PathVariable long id, @RequestBody Cliente atualizacao) {
		return servicos.atualizarCliente(id, atualizacao);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> excluirCliente(@PathVariable long id) {
		servicos.excluirCliente(id);
        return ResponseEntity.noContent().build();
	}
}