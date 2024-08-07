package com.autobots.automanager.modelo;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.autobots.automanager.entidades.Cliente;

public class ClienteValidador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();
    private EnderecoValidador enderecoValidador = new EnderecoValidador();
    private TelefoneValidador telefoneValidador = new TelefoneValidador();
    private DocumentoValidador documentoValidador = new DocumentoValidador();
    private static final int LIMITE_NOME = 180;
    private static final int LIMITE_NOME_SOCIAL = 180;
    private static final int LIMITE_IDADE_MINIMA = 18;

    public List<String> validar(Cliente cliente) {
        List<String> erros = new ArrayList<>();
        String nome = cliente.getNome();
        String nomeSocial = cliente.getNomeSocial();
        Date dataCadastro = cliente.getDataCadastro();
        Date dataNascimento = cliente.getDataNascimento();
        
        if (verificador.verificar(nome)) {
            erros.add("Nome não pode estar vazio.");
        } else if (nome.length() > LIMITE_NOME) {
            erros.add("Nome deve ter no máximo " + LIMITE_NOME + " caracteres.");
        }

        if (!verificador.verificar(nomeSocial) && nomeSocial.length() > LIMITE_NOME_SOCIAL) {
        	erros.add("Nome social deve ter no máximo " + LIMITE_NOME_SOCIAL + " caracteres.");        	
        }

        if (verificador.verificar(dataCadastro.toString())){
        	erros.add("Data de cadastro não pode estar vazia.");        	
        } else if (dataCadastro.after(new Date())) {
            erros.add("Data de cadastro não pode ser uma data no futuro.");}

        if (verificador.verificar(dataNascimento.toString())){
        	erros.add("Data de nascimento não pode estar vazia.");        	
        } else {
            LocalDate nascimento = LocalDate.ofInstant(dataNascimento.toInstant(), ZoneId.systemDefault());
        	LocalDate hoje = LocalDate.now();
            LocalDate restricaoIdade = hoje.minusYears(LIMITE_IDADE_MINIMA);
            if (nascimento.isAfter(hoje)) {
                erros.add("Data de nascimento não pode ser uma data no futuro.");
            } else if (nascimento.isAfter(restricaoIdade)) {
                erros.add("O cliente deve ter pelo menos " + LIMITE_IDADE_MINIMA + " anos.");
            }
        }

        if (cliente.getDocumentos() != null) {
            cliente.getDocumentos().forEach(documento -> {
            	if (documento != null) {
            		erros.addAll(documentoValidador.validar(documento));
            	}
            });
        }

        if (cliente.getEndereco() != null) {
            erros.addAll(enderecoValidador.validar(cliente.getEndereco()));
        }

        if (cliente.getTelefones() != null) {
            cliente.getTelefones().forEach(telefone -> {
            	if (telefone != null) {
            		erros.addAll(telefoneValidador.validar(telefone));
            	}
            });
        }

        return erros;
    }
}