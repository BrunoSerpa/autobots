package com.autobots.automanager.entidades;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Endereco {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 180, nullable = true)
	private String estado;
	@Column(length = 300, nullable = true)
	private String cidade;
	@Column(length = 300, nullable = true)
	private String bairro;
	@Column(length = 300, nullable = true)
	private String rua;
	@Column(length = 5, nullable = false)
	private String numero;
	@Column(length = 8, nullable = false)
	private String codigoPostal;
	@Column(unique = false, nullable = true)
	private String informacoesAdicionais;
}