package com.projeto.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.projeto.model.enums.VotoEnum;

@Entity
@Table(name = "votacao")
public class Votacao implements Serializable {

	private static final long serialVersionUID = 7336991692223313361L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "voto", columnDefinition = "VARCHAR(10)", nullable = false)
	private String voto = VotoEnum.BRANCO.getDescricao();

	@Column(name = "cpf_associado", columnDefinition = "VARCHAR(11)")
	private String cpfAssociado;

	@Column(name = "id_sessao")
	private Long idSessao;
	
	public VotoEnum getVoto() {
		return VotoEnum.toEnum(this.voto);
	}

	public void setVoto(VotoEnum voto) {
		this.voto = voto.getDescricao();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(Long idSessao) {
		this.idSessao = idSessao;
	}

	public String getCpfAssociado() {
		return cpfAssociado;
	}

	public void setCpfAssociado(String cpfAssociado) {
		this.cpfAssociado = cpfAssociado;
	}

}
