package edu.Ip1.cc.entidade;

/**
 * Classe criada para calcular impostos do parana.
 * 
 * @author jean, rodrigo
 *
 */
public class ImpostoParana extends Imposto {

	private static final Double IMPOSTOPARANA = 0.05;
	
	
	public ImpostoParana(Double valor) {
		super(valor);
	}

	@Override
	public Double calcularImpostoEstadual() {
		
		return  this.valor* IMPOSTOPARANA;
	}
	

	
}


