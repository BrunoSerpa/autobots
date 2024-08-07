package com.autobots.automanager.modelo;

import java.util.ArrayList;
import java.util.List;

import com.autobots.automanager.entidades.Endereco;

public class EnderecoValidador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    private static final int LIMITE_ESTADO = 180;
    private static final int LIMITE_CIDADE = 300;
    private static final int LIMITE_BAIRRO = 300;
    private static final int LIMITE_RUA = 300;
    private static final int LIMITE_NUMERO = 5;
    private static final int LIMITE_CODIGO_POSTAL = 8;

    public List<String> validar(Endereco endereco) {
        List<String> erros = new ArrayList<>();
        String bairro = endereco.getBairro();
        String cidade = endereco.getCidade();
        String codigoPostal = endereco.getCodigoPostal();
        String estado = endereco.getEstado();
        String numero = endereco.getNumero();
        String rua = endereco.getRua();

        if (!verificador.verificar(estado) && estado.length() > LIMITE_ESTADO) {
            erros.add("Estado deve ter no máximo " + LIMITE_ESTADO + " caracteres.");
        }
        
        if (!verificador.verificar(cidade) && cidade.length() > LIMITE_CIDADE) {
            erros.add("Cidade deve ter no máximo " + LIMITE_CIDADE + " caracteres.");
        }

        if (!verificador.verificar(bairro) && bairro.length() > LIMITE_BAIRRO) {
            erros.add("Bairro deve ter no máximo " + LIMITE_BAIRRO + " caracteres.");
        }

        if (!verificador.verificar(rua) && rua.length() > LIMITE_RUA) {
            erros.add("Rua deve ter no máximo " + LIMITE_RUA + " caracteres.");
        }

        if (verificador.verificar(numero)) {
        	erros.add("Número não pode ser nula.");
        } else if (numero.length() > LIMITE_NUMERO) {
            erros.add("Número deve ter no máximo " + LIMITE_NUMERO + " caracteres.");
        }
        
        if (verificador.verificar(codigoPostal)) {
        	erros.add("Código Postal não pode ser nula.");
        } else if (codigoPostal.length() > LIMITE_CODIGO_POSTAL) {
            erros.add("Código Postal deve ter no máximo " + LIMITE_CODIGO_POSTAL + " caracteres.");
        }

        return erros;
    }
}