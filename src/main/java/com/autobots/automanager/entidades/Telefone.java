package com.autobots.automanager.entidades;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class Telefone {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String ddd;
	@Column
	private String numero;
}