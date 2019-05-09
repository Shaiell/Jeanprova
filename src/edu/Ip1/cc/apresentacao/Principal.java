package edu.Ip1.cc.apresentacao;

import java.util.ArrayList;
import java.util.Collections;
import edu.Ip1.cc.entidade.ComparadorPorNome;
import edu.Ip1.cc.entidade.ComparadorPorValor;
import edu.Ip1.cc.entidade.Empresa;
import edu.Ip1.cc.entidade.Imposto;
import edu.Ip1.cc.entidade.NotaFiscal;
import edu.Ip1.cc.apresentacao.Console;

/**
 * Classe para execução do programa.
 * 
 * @author Jean, Rodrigo
 * @version 4.0
 *
 */

public class Principal {
	/**
	 * Função para inicialização do programa.
	 * 
	 * @param args
	 */

	public static void main(String[] args) {

		Integer notaFiscal = 0;
		ArrayList<Empresa> empresas = new ArrayList<Empresa>();

		String[] opcao = { "Cadastrar Empresa", "Consultar empresas", "Excluir Empresas", "Emitir Nota",
				"Cancelar Nota", "Relatorio de Notas." };

		boolean repitir = true;

		do {
			int menu = Console.mostrarMenu(opcao, "\nOpções", null);

			switch (menu) {

			case 1:
				try {
					Empresa empresa = cadastrarEmpresa(empresas);
					empresas.add(empresa);
					System.out.println("\nEmpresa Cadastrada. ");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				break;

			case 2:
				try {
					consultarEmpresas(empresas);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				try {
					empresas = excluirEmpresa(empresas);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				break;

			case 4:
				try {
					notaFiscal = emitirNota(empresas, notaFiscal);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				break;

			case 5:
				try {
					cancelarNota(empresas);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				break;

			case 6:

				listarNotas(empresas);
				break;

			case -1:
				System.out.println("Finalizando o Sistema...");
				repitir = false;
				break;
			}
		} while (repitir == true);
	}

	/**
	 * Metodo para mostrar os menus de listar as notas.
	 * 
	 * @param empresas
	 */
	private static void listarNotas(ArrayList<Empresa> empresas) {

		String[] opcao = { "Notas fiscais validas por empresa", "Notas fiscais canceladas por empresas",
				"Todas as notas ordenadas por valor" };

		boolean repitir = true;

		do {
			int menu = Console.mostrarMenu(opcao, "Opções", null);

			switch (menu) {

			case 1:

				try {
					notasValidas(empresas);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				break;

			case 2:

				try {
					notasCanceladas(empresas);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				break;

			case 3:
				notasOrdenarValor(empresas);

				break;

			case -1:
				System.out.println("Retornando ao menu principal. . .");
				repitir = false;
				break;
			}
		} while (repitir == true);
	}

	/**
	 * Metodo para listar ordenando pelo valor com imposto em orddem crescente.
	 * 
	 * @param empresas vetor com todas as empresas.
	 */
	private static void notasOrdenarValor(ArrayList<Empresa> empresas) {
		if (empresas.isEmpty()) {
			System.out.println("Nao existe empresas cadastradas.");
		} else {
			ArrayList<NotaFiscal> todasAsNotas = new ArrayList<>();
			ArrayList<NotaFiscal> notas = new ArrayList<>();
			for (Empresa empresa : empresas) {
				notas = empresa.getNotasFiscais();
				for (NotaFiscal notaFiscal : notas) {
					todasAsNotas.add(notaFiscal);

				}
			}
			if (todasAsNotas.isEmpty()) {
				System.out.println("Não Existe notas cadastradas! ");
			} else {
				Collections.sort(todasAsNotas, new ComparadorPorValor());
				for (NotaFiscal notaFiscal : todasAsNotas) {
					System.out.println(notaFiscal);
				}
			}
		}

	}

	/**
	 * Metodo para listar as notas canceladas de um CNPJ especifico.
	 * 
	 * @param empresas vetor de todas as empresas.
	 * @throws Exception para quando não existe empresas cadastradas ou quando o
	 *                   cnpj informado foi incorreto.
	 */
	private static void notasCanceladas(ArrayList<Empresa> empresas) throws Exception {
		if (empresas.isEmpty()) {
			System.out.println("Nao existe empresas cadastradas.");
		} else {
			Empresa empresa = retornarEmpresa(empresas);
			ArrayList<NotaFiscal> notasCanceladas = empresa.getNotasFiscaisCanceladas();
			if (notasCanceladas.isEmpty()) {
				System.out.println("Não existe notas Canceladas! ");
			} else {
				for (NotaFiscal notaFiscal : notasCanceladas) {
					System.out.println(notaFiscal);

				}
			}
		}

	}

	/**
	 * Metodo para listar as notas validas de um CNPJ especifico.
	 * 
	 * @param empresas vetor de todas as empresas.
	 * @throws Exception para quando não existe empresas cadastradas ou quando o
	 *                   cnpj informado foi incorreto.
	 */
	private static void notasValidas(ArrayList<Empresa> empresas) throws Exception {
		if (empresas.isEmpty()) {
			System.out.println("Nao existe empresas cadastradas.");
		} else {
			Empresa empresa = retornarEmpresa(empresas);
			ArrayList<NotaFiscal> notasValidas = empresa.getNotasFiscaisValidas();
			if (notasValidas.isEmpty()) {
				System.out.println("Não existe notas Validas! ");
			} else {
				for (NotaFiscal notaFiscal : notasValidas) {
					System.out.println(notaFiscal);

				}
			}
		}

	}

	/**
	 * Matodo para excluir uma empresa, desde que ela não tenha uma nota fiscal
	 * valida
	 * 
	 * @param empresas vetor de todas as empresas.
	 * @return retorna o vetor com as impresas que não foram excluidas.
	 * @throws Exception para quando não existe empresas cadastradas ou quando o
	 *                   cnpj informado foi incorreto.
	 */
	private static ArrayList<Empresa> excluirEmpresa(ArrayList<Empresa> empresas) throws Exception {

		if (empresas.isEmpty()) {
			throw new Exception("Nao existe empresas cadastradas.");
		} else {
			Empresa empresa = retornarEmpresa(empresas);
			ArrayList<NotaFiscal> notasValidas = empresa.getNotasFiscaisValidas();

			if (notasValidas.isEmpty()) {
				System.out.println(empresa);
				empresas.remove(empresa);
				System.out.println("\nEmpresa Excluida! ");

			} else {
				throw new Exception("Não foi possivel excluir pois existem notas validas no sistema ");

			}

		}
		return empresas;
	}

	/**
	 * metodo criado para excluir uma nota de uma empresa solicitada via CNPJ.
	 * 
	 * @param empresas vetor de todas as empresas.
	 * @throws Exception para quando não existe empresas cadastradas, quando o cnpj
	 *                   informado foi incorreto ou numero da nota incorreto.
	 */

	private static void cancelarNota(ArrayList<Empresa> empresas) throws Exception {
		if (empresas.isEmpty()) {
			throw new Exception("Nao existe empresas cadastradas.");
		} else {
			Empresa empresa = retornarEmpresa(empresas);
			ArrayList<NotaFiscal> notas = empresa.getNotasFiscaisValidas();
			if (!notas.isEmpty()) {
				for (NotaFiscal notaFiscal : notas) {
					System.out.println(notaFiscal);
				}

				Integer numeroNota = Console
						.recuperaInteiro("\nInforme o Numero da Nota desejada para realização do cancelamento: ");
				Boolean ok = empresa.cancelamentoDeNota(numeroNota);
				if (ok) {
					System.out.println("\nNota Cancelada!\n");
				} else {
					System.out.println("\nNumero informado não é de uma nota fiscal valida!");
				}
			} else {
				throw new Exception("Nao existe notas validas para cancelamento.");
			}
		}

	}

	/**
	 * Metodo criado para emitir uma nota fiscal para um CNPJ informado.
	 * 
	 * @param empresas   vetor de todas as empresas.
	 * @param notaFiscal variavel objeto para criar uma nota fiscal a ser atribuida
	 *                   a uma empresa expecifica.
	 * @return ultimo numero da nota fiscal emitida para ser utilizado quando for
	 *         criado a procima nota fiscal, deixando a numeração da note fiscal
	 *         automatica.
	 * @throws Exception para quando não existe empresas cadastradas ou quando o
	 *                   cnpj informado foi incorreto
	 */
	private static Integer emitirNota(ArrayList<Empresa> empresas, Integer notaFiscal) throws Exception {
		Integer numero = null;
		if (empresas.isEmpty()) {
			System.out.println("Nao existe empresas cadastradas.");
		} else {
			Empresa empresa = retornarEmpresa(empresas);
			System.out.println(empresa);
			numero = notaFiscal + 1;
			String descricao = Console.recuperaTexto("Descrição dos produtos da nota");
			Double valor = Console.recuperaDecimal("digite o valor");
			String estado = consultarEstado();
			Imposto imposto = estadoDoImposto(valor, estado);
			NotaFiscal nota = new NotaFiscal(numero, descricao, valor, estado, imposto);
			empresa.addNotaFiscal(nota);
			System.out.println(nota);

		}
		return numero;

	}

	/**
	 * Metodo criado para selecionar qual imposto deve ser aplicado na nota fiscal
	 * emitida.
	 * 
	 * @param valor  variavel passada para calculo do imposto.
	 * @param estado variavel para seleção da criação do imposto.
	 * @return retorna o o objeto imposto para a emissão de uma nota fiscal.
	 */
	private static Imposto estadoDoImposto(Double valor, String estado) {
		String opcao = estado;
		Imposto imposto = null;
		boolean repitir = true;

		do {

			switch (opcao) {

			case "Paraná":
				imposto = new edu.Ip1.cc.entidade.ImpostoParana(valor);
				repitir = false;
				break;

			case "São Paulo":
				imposto = new edu.Ip1.cc.entidade.ImpostoSaoPaulo(valor);
				repitir = false;
				break;

			case "Santa Catarina":
				imposto = new edu.Ip1.cc.entidade.ImpostoSantaCatarina(valor);
				repitir = false;
				break;

			default:
				System.out.println("\nSelecione uma Opção Valida...\n");
				break;
			}
		} while (repitir == true);
		return imposto;
	}

	/**
	 * Metodo criado para selecionar um estado onde a nota fiscal vai ser emitida..
	 * 
	 * @return retorna um estado.
	 */
	private static String consultarEstado() {
		String[] opcao = { "Paraná", "São Paulo", "Santa Catarina" };

		String estado = null;
		boolean repitir = true;

		do {
			int menu = Console.mostrarMenuMeu(opcao, "Informe o estado:", null);
			switch (menu) {

			case 1:
				estado = "Paraná";
				repitir = false;
				break;

			case 2:
				estado = "São Paulo";
				repitir = false;
				break;

			case 3:
				estado = "Santa Catarina";
				repitir = false;
				break;

			default:
				System.out.println("\nSelecione uma Opção Valida...\n");
				break;
			}
		} while (repitir == true);
		return estado;

	}

	/**
	 * Metodo criado para criar um menu de Opções para listar as empresas
	 * cadastradas.
	 * 
	 * @param empresas vetor de todas as empresas.
	 * @throws Exception Exception para quando não existe empresas cadastradas ou
	 *                   quando o cnpj informado foi incorreto
	 */
	private static void consultarEmpresas(ArrayList<Empresa> empresas) throws Exception {
		String[] opcao = { "Listar todas as empresas (A ~ Z)", "Consultar empresa por CNPJ:" };

		boolean repitir = true;

		do {
			int menu = Console.mostrarMenu(opcao, "\nOpções", null);
			switch (menu) {

			case 1:
				consultarTodasAsEmpresas(empresas);
				break;

			case 2:
				try {
					consultarEmpresaPorCnpj(empresas);
				} catch (Exception retorno) {
					System.out.println(retorno.getMessage());
				}
				break;

			case -1:
				System.out.println("Retornando ao menu anterior...");
				repitir = false;
				break;
			}
		} while (repitir == true);

	}

	/**
	 * Metodo criado para consultar uma empresa com o devido CNPJ informado.
	 * 
	 * @param empresas vetor de todas as empresas.
	 * @throws Exception para quando não existe empresas cadastradas ou quando o
	 *                   cnpj informado foi incorreto
	 */
	private static void consultarEmpresaPorCnpj(ArrayList<Empresa> empresas) throws Exception {
		if (empresas.isEmpty()) {
			System.out.println("Não existe Empresas Cadastradas");
		} else {
			Empresa empresa = retornarEmpresa(empresas);

			System.out.println(empresa);

		}

	}

	/**
	 * Metodo criado para listar todas as empresas cadastradas classificando por
	 * orddem alfabetica.
	 * 
	 * @param empresas vetor de todas as empresas.
	 * @throws Exception para quando não existe empresas cadastradas.
	 */
	private static void consultarTodasAsEmpresas(ArrayList<Empresa> empresas) throws Exception {
		ArrayList<Empresa> listaOrdenada = empresas;

		if (listaOrdenada.isEmpty()) {
			System.out.println("\nNão existe Empresas Cadastradas");
		} else {

			Collections.sort(listaOrdenada, new ComparadorPorNome());

			for (Empresa empresa : listaOrdenada) {

				System.out.println(empresa);
			}
		}

	}

	/**
	 * Metodo criado para efetuar o cadastro das empresas..
	 * 
	 * @param empresas Vetor de todas as empresas cadastradas.
	 * @return retorna um vetor Objeto de Empresas cadastradas.
	 * @throws Exception para quando o CNPJ ja é existente.
	 */
	private static Empresa cadastrarEmpresa(ArrayList<Empresa> empresas) throws Exception {

		String nome = Console.recuperaTexto("Infome o Nome da Empresa");
		String cnpj = Console.recuperaTexto("Informe CNPJ");

		for (Empresa empresa : empresas) {
			if (empresa.getCnpj().equals(cnpj)) {
				throw new Exception("CNPJ já Está Cadastrado!");
			}
		}
		Empresa retorno = new Empresa(nome, cnpj);

		return retorno;

	}

	/**
	 * Metodo criado para buscar uma empresa por cnpj informado.
	 * 
	 * @param empresas vetor de todas as empresas.
	 * @return retorna uma empresa.
	 * @throws Exception para quando não existe empresas cadastradas ou quando o
	 *                   cnpj informado foi incorreto
	 */
	private static Empresa retornarEmpresa(ArrayList<Empresa> empresas) throws Exception {
		String cnpj = Console.recuperaTexto("Informe o CNPJ que deseja buscar.");
		for (Empresa empresa : empresas) {
			if (empresa.getCnpj().equals(cnpj)) {

				return empresa;
			}
		}
		throw new Exception("CNPJ informado não é Valido");
	}

}
