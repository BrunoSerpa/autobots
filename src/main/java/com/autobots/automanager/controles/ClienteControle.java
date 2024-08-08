package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.*;
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
	
	@PutMapping("/{clienteId}/endereco")
    public ResponseEntity<?> atualizarEndereco(@PathVariable long clienteId, @RequestBody Endereco endereco) {
        return servicos.atualizarEndereco(clienteId, endereco);
    }

    @DeleteMapping("/{clienteId}/endereco")
    public ResponseEntity<?> excluirEndereco(@PathVariable long clienteId) {
        return servicos.excluirEndereco(clienteId);
    }

    @PostMapping("/{clienteId}/documentos")
    public ResponseEntity<?> adicionarDocumento(@PathVariable Long clienteId, @RequestBody Documento documento) {
        return servicos.adicionarDocumento(clienteId, documento);
    }

    @PutMapping("/{clienteId}/documentos/{documentoId}")
    public ResponseEntity<?> atualizarDocumento(@PathVariable Long clienteId, @RequestBody Documento documentoAtualizado, @PathVariable Long documentoId) {
    	documentoAtualizado.setId(documentoId);
        return servicos.atualizarDocumento(clienteId, documentoAtualizado);
    }

    @DeleteMapping("/{clienteId}/documentos/{documentoId}")
    public ResponseEntity<?> deletarDocumento(@PathVariable Long clienteId, @PathVariable Long documentoId) {
        return servicos.deletarDocumento(clienteId, documentoId);
    }
    
    @PostMapping("/{clienteId}/telefones")
    public ResponseEntity<?> adicionarTelefone(@PathVariable Long clienteId, @RequestBody Telefone telefone) {
        return servicos.adicionartelefone(clienteId, telefone);
    }

    @PutMapping("/{clienteId}/telefones/{telefoneId}")
    public ResponseEntity<?> atualizarTelefone(@PathVariable Long clienteId, @RequestBody Telefone telefoneAtualizado, @PathVariable Long telefoneId) {
    	telefoneAtualizado.setId(telefoneId);
        return servicos.atualizartelefone(clienteId, telefoneAtualizado);
    }

    @DeleteMapping("/{clienteId}/telefones/{telefoneId}")
    public ResponseEntity<?> deletarTelefone(@PathVariable Long clienteId, @PathVariable Long telefoneId) {
        return servicos.deletarTelefone(clienteId, telefoneId);
    }
}