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
@Table(name = "pauta")
public class Pauta implements Serializable {
	
	private static final long serialVersionUID = 3454734434633235618L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	

	@Column(name = "descricao", nullable = false)
	private String descricao;


	@Column(name = "data", nullable = false)
	private Date data;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Date getData() {
		return data;
	}


	public void setData(Date data) {
		this.data = data;
	}
	
	
	
}
