package com.autobots.automanager.entidades;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Documento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 180,nullable = false)
	private String tipo;
	@Column(length = 20, nullable = false, unique = true)
	private String numero;
}