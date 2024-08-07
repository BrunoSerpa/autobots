package com.autobots.automanager.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.autobots.automanager.entidades.Telefone;

public class TelefoneValidador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    private static final int LIMITE_DDD = 3;
    private static final int LIMITE_NUMERO = 10;
    private static final Pattern SOMENTE_NUMEROS = Pattern.compile("^\\d*$");

    public List<String> validar(Telefone telefone) {
        List<String> erros = new ArrayList<>();

        String ddd = telefone.getDdd();
        String numero = telefone.getNumero();

        if (verificador.verificar(ddd)) {
        	erros.add("DDD não pode estar vazio.");
        } else if(SOMENTE_NUMEROS.matcher(ddd).matches()) {
        	erros.add("DDD deve ter no máximo " + LIMITE_DDD + " caracteres.");
        } else if (ddd.length() > LIMITE_DDD) {
            erros.add("DDD deve ter no máximo " + LIMITE_DDD + " caracteres.");
        }

        if (verificador.verificar(numero)) {
        	erros.add("Número não pode estar vazio.");
        } else if(SOMENTE_NUMEROS.matcher(numero).matches()) {
        	erros.add("Número deve ter no máximo " + LIMITE_DDD + " caracteres.");
        } else if (numero.length() > LIMITE_NUMERO) {
            erros.add("Número deve ter no máximo " + LIMITE_DDD + " caracteres.");
        }

        return erros;
    }
}