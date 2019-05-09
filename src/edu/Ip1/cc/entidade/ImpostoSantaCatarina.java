package edu.Ip1.cc.entidade;
/**
 * Classe criada para calcular impostos de santa catarina.
 * 
 * @author jean, rodrigo
 *
 */

public class ImpostoSantaCatarina extends Imposto  {

	private static final Double IMPOSTOSANTA = 0.10;
	public ImpostoSantaCatarina(Double valor) {
		super(valor);

	}
@Override
public Double calcularImpostoEstadual() {
	
	return this.valor * IMPOSTOSANTA;
}
	
	
}
