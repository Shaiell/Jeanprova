package edu.Ip1.cc.entidade;

import java.util.Comparator;

/**
 * Classe que faz a ordenação por ordem alfabetica do nome da empresa.
 * 
 * @author Jean, Rodrigo
 *
 */

public class ComparadorPorNome implements Comparator<Empresa> {

	@Override
	public int compare(Empresa o1, Empresa o2) {
		// TODO Auto-generated method stub
		return o1.getNome().compareTo(o2.getNome());		
	}
	
}

