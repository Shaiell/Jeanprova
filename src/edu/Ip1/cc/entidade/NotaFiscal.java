package edu.Ip1.cc.entidade;

import java.util.Date;

/**
 * Classe criada para criação de uma nota fiscal.
 * 
 * @author jean, rodrigo
 *
 */
public class NotaFiscal {

	private Integer numero;
	
	private String descricao;
	
	private Date dataEmissao;
	
	private Imposto imposto;
	
	private Double valor;
	
	private Double valorComImposto;
	
	private boolean cancelada;
	
	private String estado;

	public NotaFiscal(Integer numero, String descricao,  Double valor, String estado ,Imposto imposto) {
		
		this.numero = numero;
		this.descricao = descricao;
		this.imposto = imposto;
		this.valor = valor;
		this.dataEmissao = new Date();
		this.valorComImposto = this.valor+imposto.calcularImpostoTotal();
		this.cancelada = false;
		this.estado = estado;
	
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Imposto getImposto() {
		return imposto;
	}

	public void setImposto(Imposto imposto) {
		this.imposto = imposto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorComImposto() {
		return valorComImposto;
	}

	public void setValorComImposto(Double valorComImposto) {
		this.valorComImposto = valorComImposto;
	}

	public boolean getCancelada() {
		return cancelada;
	}

	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}
	
	
	public NotaFiscal(String estado) {
		super();
		this.setEstado(estado);
	}

	@Override
	public String toString() {
		
		return "\nNumero da nota: " + this.numero  + "\nDescricao: " + this.descricao +  "\nData: " +
		this.dataEmissao + "\nEstado de emissão da nota: "+ this.estado+ "\nValor do Produto: " + this.valor + "\nImposto Federal: " + 
		this.imposto.calcularImpostoFederal() + "\nImposto Estadual: " + 
		this.imposto.calcularImpostoEstadual() + "\nTotal de Imposto: " + this.imposto.calcularImpostoTotal()
		+ "\nvalor total do Produto com Imposto: " + this.valorComImposto+ "\n" ; 
	}
	
	
	

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
