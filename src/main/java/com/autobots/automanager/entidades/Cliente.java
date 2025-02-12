package com.autobots.automanager.entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 180)
	private String nome;

	@Column(length = 180)
	private String nomeSocial;

	@Column(nullable = false)
	private Date dataNascimento;

	@Column(nullable = false)
	private Date dataCadastro;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Documento> documentos = new ArrayList<>();

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Endereco endereco;

	@OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Telefone> telefones = new ArrayList<>();
}