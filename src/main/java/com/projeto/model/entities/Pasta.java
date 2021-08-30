package com.projeto.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pasta")
public class Pasta implements Serializable {
	
	private static final long serialVersionUID = 3454734434633235618L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	

	@Column(name = "descricao")
	private String descricao;


	@Column(name = "data")
	private Date data;
	
}
