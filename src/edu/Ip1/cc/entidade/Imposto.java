package edu.Ip1.cc.entidade;

/**
 * Classe criada para criar um imposto.
 * 
 * @author jean, rodrigo
 *
 */

public abstract class Imposto {
	
	private static final Double ALIQUOTAFEDERAL = 0.15;
	
	protected Double valor;

	public Imposto(Double valor) {
		this.valor = valor;
	}
	
	public Double calcularImpostoTotal() {
		return calcularImpostoEstadual() + calcularImpostoFederal();
	}
	
	public Double calcularImpostoFederal() {
		return this.valor*ALIQUOTAFEDERAL;
	}
	
	public abstract Double calcularImpostoEstadual();
	
	

}
