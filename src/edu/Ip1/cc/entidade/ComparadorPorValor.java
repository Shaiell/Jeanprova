package edu.Ip1.cc.entidade;

import java.util.Comparator;

/** 
 * Classe que faz a ordenação por valor final da nota.
 * 
 * @author Jean, Rodrigo
 *
 */

public class ComparadorPorValor implements Comparator<NotaFiscal>{
	

	@Override
	public int compare(NotaFiscal o1, NotaFiscal o2) {
		// TODO Auto-generated method stub
		return o1.getValorComImposto().compareTo(o2.getValorComImposto());		
	}
	
}
