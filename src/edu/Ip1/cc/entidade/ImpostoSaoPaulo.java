package edu.Ip1.cc.entidade;

/**
 * Classe criada para calcular impostos de são paulo.
 * 
 * @author jean, rodrigo
 *
 */

public class ImpostoSaoPaulo extends Imposto {

	private static final Double IMPOSTOPAULO = 0.18;

	public ImpostoSaoPaulo(Double valor) {
		super(valor);

	}
	
	@Override
	public Double calcularImpostoEstadual() {
	
		return this.valor * IMPOSTOPAULO;

}
	
}
