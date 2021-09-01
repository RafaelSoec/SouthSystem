package com.projeto.model.dtos;

public class ResultadoVotacaoDTO {
	private Double valorTotal;
	private Double valorTotalContra;
	private Double valorTotalAFavor;
	
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public Double getValorTotalContra() {
		return valorTotalContra;
	}
	public void setValorTotalContra(Double valorTotalContra) {
		this.valorTotalContra = valorTotalContra;
	}
	public Double getValorTotalAFavor() {
		return valorTotalAFavor;
	}
	public void setValorTotalAFavor(Double valorTotalAFavor) {
		this.valorTotalAFavor = valorTotalAFavor;
	}
	
}
