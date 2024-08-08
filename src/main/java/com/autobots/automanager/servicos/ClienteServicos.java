package com.autobots.automanager.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelo.Atualizador;
import com.autobots.automanager.modelo.ClienteValidador;
import com.autobots.automanager.modelo.DocumentoValidador;
import com.autobots.automanager.modelo.EnderecoValidador;
import com.autobots.automanager.modelo.TelefoneValidador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@Service
public class ClienteServicos {
	@Autowired
	private ClienteRepositorio repositorio;

	@Autowired
	private DocumentoRepositorio repositorioDocumento;
	@Autowired
	private TelefoneRepositorio repositorioTelefone;

	private ClienteValidador validador = new ClienteValidador();
	private DocumentoValidador validadorDocumento = new DocumentoValidador();
	private EnderecoValidador validadorEndereco = new EnderecoValidador();
	private TelefoneValidador validadorTelefone = new TelefoneValidador();
	
	public Cliente salvarCliente(Cliente cliente) {
		return repositorio.save(cliente);
	}
	
	public List<Cliente> listarClientes() {
		return repositorio.findAll();
	}
	
	public Optional<Cliente> buscarPorId(Long id) {
		return repositorio.findById(id);
	}
	
	public ResponseEntity<?> atualizarCliente(Long id, Cliente atualizacao) {
        return repositorio.findById(id).map(cliente -> {
            Atualizador atualizador = new Atualizador();
            atualizador.atualizarCliente(cliente, atualizacao);
            List<String> erros = validador.validar(cliente);

            if (!erros.isEmpty()) {
                return ResponseEntity.badRequest().body(erros);
            }

            Cliente clienteSalvo = salvarCliente(cliente);
            return ResponseEntity.ok(clienteSalvo);
        }).orElse(ResponseEntity.notFound().build());
    }

    public void excluirCliente(Long id) {
    	repositorio.deleteById(id);
    }
    
    public ResponseEntity<?> atualizarEndereco(Long clienteId, Endereco enderecoAtualizado) {
        return repositorio.findById(clienteId).map(cliente -> {
            Atualizador atualizador = new Atualizador();
            Endereco enderecoAtual = cliente.getEndereco();
            if (enderecoAtual != null) {
                atualizador.atualizarEndereco(enderecoAtual, enderecoAtualizado);
            } else {
                cliente.setEndereco(enderecoAtualizado);
            }

            List<String> erros = validadorEndereco.validar(enderecoAtualizado);
            if (!erros.isEmpty()) {
                return ResponseEntity.badRequest().body(erros);
            }

            Cliente clienteSalvo = salvarCliente(cliente);
            return ResponseEntity.ok(clienteSalvo);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> excluirEndereco(Long clienteId) {
        return repositorio.findById(clienteId).map(cliente -> {
            cliente.setEndereco(null);
            salvarCliente(cliente);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
    
    public ResponseEntity<?> adicionarDocumento(Long clienteId, Documento documento) {
        return repositorio.findById(clienteId).map(cliente -> {
            List<String> erros = validadorDocumento.validar(documento);
            if (!erros.isEmpty()) {
                return ResponseEntity.badRequest().body(erros);
            }
            cliente.getDocumentos().add(documento);
            Cliente clienteSalvo = salvarCliente(cliente);
            return ResponseEntity.ok(clienteSalvo);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> atualizarDocumento(Long clienteId, Documento documentoAtualizado) {
        return repositorio.findById(clienteId).map(cliente -> {
            List<Documento> documentos = cliente.getDocumentos();
            for (Documento documento : documentos) {
                if (documento.getId().equals(documentoAtualizado.getId())) {
                    Atualizador atualizador = new Atualizador();
                    atualizador.atualizarDocumento(documento, documentoAtualizado);

                    List<String> erros = validadorDocumento.validar(documento);
                    if (!erros.isEmpty()) {
                        return ResponseEntity.badRequest().body(erros);
                    }

                    Cliente clienteSalvo = salvarCliente(cliente);
                    return ResponseEntity.ok(clienteSalvo);
                }
            }
            return ResponseEntity.notFound().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deletarDocumento(Long clienteId, Long documentoId) {
        return repositorio.findById(clienteId).map(cliente -> {
            List<Documento> documentos = cliente.getDocumentos();
            for (Documento documento : documentos) {
                if (documento.getId().equals(documentoId)) {
                    documentos.remove(documento);
                    salvarCliente(cliente);
                    repositorioDocumento.delete(documento);
                    return ResponseEntity.noContent().build();
                }
            }
            return ResponseEntity.notFound().build();
        }).orElse(ResponseEntity.notFound().build());
    }
    
    public ResponseEntity<?> adicionartelefone(Long clienteId, Telefone telefone) {
        return repositorio.findById(clienteId).map(cliente -> {
            List<String> erros = validadorTelefone.validar(telefone);
            if (!erros.isEmpty()) {
                return ResponseEntity.badRequest().body(erros);
            }
            cliente.getTelefones().add(telefone);
            Cliente clienteSalvo = salvarCliente(cliente);
            return ResponseEntity.ok(clienteSalvo);
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> atualizartelefone(Long clienteId, Telefone telefoneAtualizado) {
        return repositorio.findById(clienteId).map(cliente -> {
            List<Telefone> telefones = cliente.getTelefones();
            for (Telefone telefone : telefones) {
                if (telefone.getId().equals(telefoneAtualizado.getId())) {
                    Atualizador atualizador = new Atualizador();
                    atualizador.atualizarTelefone(telefone, telefoneAtualizado);

                    List<String> erros = validadorTelefone.validar(telefone);
                    if (!erros.isEmpty()) {
                        return ResponseEntity.badRequest().body(erros);
                    }

                    Cliente clienteSalvo = salvarCliente(cliente);
                    return ResponseEntity.ok(clienteSalvo);
                }
            }
            return ResponseEntity.notFound().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<?> deletarTelefone(Long clienteId, Long telefoneId) {
        return repositorio.findById(clienteId).map(cliente -> {
            List<Telefone> telefones = cliente.getTelefones();
            for (Telefone telefone : telefones) {
                if (telefone.getId().equals(telefoneId)) {
                	telefones.remove(telefone);
                    salvarCliente(cliente);
                    repositorioTelefone.delete(telefone);
                    return ResponseEntity.noContent().build();
                }
            }
            return ResponseEntity.notFound().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}