package edu.Ip1.cc.entidade;

import java.util.ArrayList;

/**
 * Classe que representa uma empresa e seus dados.
 * 
 * @author Jean, Rodrigo
 *
 */

public class Empresa {

	private String nome;
	private String cnpj;
	private ArrayList<NotaFiscal> notasFiscais;

	public Empresa(String nome, String cnpj) {
		this.nome = nome;
		this.cnpj = cnpj;
		this.notasFiscais = new ArrayList<NotaFiscal>();
	}

	/**
	 * Metodo responsavel por listar as notas ficais validas
	 * 
	 * @return retorna um vetor de notas fiscais com todas as notas fiscais validas.
	 */
	public ArrayList<NotaFiscal> getNotasFiscaisValidas() {
		ArrayList<NotaFiscal> notasValidas = new ArrayList<NotaFiscal>();

		for (NotaFiscal notaFiscal : this.notasFiscais) {
			if (!notaFiscal.getCancelada()) {
				notasValidas.add(notaFiscal);
			}
		}
		return notasValidas;
	}

	/**
	 * Metodo responsavel por listar as notas fiscais canceladas.
	 * 
	 * @returnretorna um vetor de notas fiscais com todas as notas fiscais
	 *                candeladas.
	 */
	public ArrayList<NotaFiscal> getNotasFiscaisCanceladas() {
		ArrayList<NotaFiscal> notasCanceladas = new ArrayList<NotaFiscal>();

		for (NotaFiscal notaFiscal : this.notasFiscais) {
			if (notaFiscal.getCancelada()) {
				notasCanceladas.add(notaFiscal);
			}
		}
		return notasCanceladas;
	}

	/**
	 * Metodo criado para adicionar uma nota fiscal no array de notas da empresa.
	 * 
	 * @param nota uma nota para ser adcionada no vetor.
	 */
	public void addNotaFiscal(NotaFiscal nota) {
		notasFiscais.add(nota);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public ArrayList<NotaFiscal> getNotasFiscais() {
		return notasFiscais;
	}

	public void setNotasFiscais(ArrayList<NotaFiscal> notasFiscais) {
		this.notasFiscais = notasFiscais;
	}

	@Override
	public String toString() {

		return "\nNome: " + this.nome + "\nCnpj: " + this.cnpj + "\nNotas Fiscais: " + this.notasFiscais.size() + "\n";
	}

	public Boolean cancelamentoDeNota(Integer numeroNota) throws Exception {

		for (NotaFiscal notaFiscal : this.notasFiscais) {
			if (notaFiscal.getNumero().equals(numeroNota) && notaFiscal.getCancelada() != true) {
				notaFiscal.setCancelada(true);
				return true;
			}

		}
		for (NotaFiscal notaFiscal : this.notasFiscais) {
			if (notaFiscal.getNumero().equals(numeroNota) && notaFiscal.getCancelada() == true) {
				throw new Exception("Nota fiscal já esta Cancelada");
			}
		}
		return false;
	}

}
