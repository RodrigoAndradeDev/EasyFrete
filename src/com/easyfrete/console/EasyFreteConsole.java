package com.easyfrete.console;

import java.util.Scanner;
import java.util.UUID;

import com.easyfrete.adapter.*;
import com.easyfrete.builder.EntregaBuilder;
import com.easyfrete.enums.StatusEntrega;
import com.easyfrete.enums.TipoEntrega;
import com.easyfrete.factory.*;
import com.easyfrete.model.Cliente;
import com.easyfrete.model.Endereco;
import com.easyfrete.model.Entrega;
import com.easyfrete.observer.EntregaConsoleObserver;
import com.easyfrete.observer.EntregaHistoricoObserver;
import com.easyfrete.proxy.CalculoFreteProxy;
import com.easyfrete.service.ServicoCalculoFrete;
import com.easyfrete.service.ServicoEntrega;
import com.easyfrete.strategy.CalculoFrete;

public class EasyFreteConsole {

	private final Scanner scanner = new Scanner(System.in);

	private final ServicoEntrega servicoEntrega = new ServicoEntrega();
	private final ServicoCalculoFrete servicoCalculoFrete = new ServicoCalculoFrete();

	private final EntregaConsoleObserver consoleObserver = new EntregaConsoleObserver();

	private final EntregaHistoricoObserver historicoObserver = new EntregaHistoricoObserver();

	private boolean consoleObserverAtivo = false;
	private boolean historicoObserverAtivo = false;

	// ==========================================
	// INICIALIZAÇÃO
	// ==========================================

	public void start() {

		int opcao;

		System.out.println("========================================");
		System.out.println("              EASYFRETE");
		System.out.println("       Sistema de Gerenciamento");
		System.out.println("              de Entregas");
		System.out.println("========================================");

		do {
			exibirMenu();

			opcao = lerInteiro("Escolha uma opção: ");

			try {

				switch (opcao) {

				case 1:
					cadastrarEntrega();
					break;

				case 2:
					listarEntregas();
					break;

				case 3:
					buscarEntrega();
					break;

				case 4:
					calcularFrete();
					break;

				case 5:
					atualizarStatus();
					break;

				case 6:
					gerenciarNotificacoes();
					break;

				case 0:
					System.out.println("\nEncerrando o EasyFrete...");
					break;

				default:
					System.out.println("\nOpção inválida.");
				}

			} catch (Exception e) {
				System.out.println("\nErro: " + e.getMessage());
			}

		} while (opcao != 0);

		scanner.close();
	}

	// ==========================================
	// MENU PRINCIPAL
	// ==========================================
	private void exibirMenu() {

		System.out.println("\n========================================");
		System.out.println("             MENU PRINCIPAL");
		System.out.println("========================================");

		System.out.println("1. Cadastrar nova entrega");
		System.out.println("2. Listar entregas");
		System.out.println("3. Buscar entrega por ID");
		System.out.println("4. Calcular frete");
		System.out.println("5. Atualizar status de entrega");
		System.out.println("6. Gerenciar notificações");
		System.out.println("0. Sair");

		System.out.println("========================================");
	}

	// ==========================================
	// 1. CADASTRAR ENTREGA (Builder)
	// ==========================================
	private void cadastrarEntrega() {

		System.out.println("\n========== CADASTRAR ENTREGA ==========");
		System.out.println("\n--- Dados do cliente ---");

		String nome = lerTexto("Nome: ");
		String contato = lerTexto("Contato: ");

		Cliente cliente = new Cliente(UUID.randomUUID(), nome, contato);

		System.out.println("\n--- Endereço de origem ---");
		Endereco origem = cadastrarEndereco();

		System.out.println("\n--- Endereço de destino ---");
		Endereco destino = cadastrarEndereco();

		System.out.println("\n--- Dados da entrega ---");
		double peso = lerDoublePositivo("Peso (kg): ");
		double distancia = lerDoublePositivo("Distância (km): ");

		TipoEntrega tipo = selecionarTipoEntrega();

		Entrega entrega = new EntregaBuilder().cliente(cliente).origem(origem).destino(destino).peso(peso)
				.distancia(distancia).tipo(tipo).build();

		servicoEntrega.cadastrarEntrega(entrega);

		System.out.println("\nEntrega cadastrada com sucesso!");
		System.out.println(entrega);
	}

	private Endereco cadastrarEndereco() {

		String rua = lerTexto("Rua: ");
		String cidade = lerTexto("Cidade: ");
		String estado = lerTexto("Estado: ");
		String cep = lerTexto("CEP: ");

		return new Endereco(rua, cidade, estado, cep);
	}

	private TipoEntrega selecionarTipoEntrega() {

		System.out.println("\n--- Modalidades de entrega ---");

		System.out.println("1. Econômica");
		System.out.println("2. Expressa");
		System.out.println("3. Agendada");
		System.out.println("4. Terceirizada");

		int opcao = lerInteiro("Escolha a modalidade: ");

		switch (opcao) {
		case 1:
			return TipoEntrega.ECONOMICA;

		case 2:
			return TipoEntrega.EXPRESSA;

		case 3:
			return TipoEntrega.AGENDADA;

		case 4:
			return TipoEntrega.TERCEIRIZADA;

		default:
			throw new IllegalArgumentException("Modalidade de entrega inválida.");
		}
	}

	// ==========================================
	// 2. LISTAR ENTREGAS
	// ==========================================
	private void listarEntregas() {

		System.out.println("\n========== ENTREGAS CADASTRADAS ==========");
		servicoEntrega.listarEntregas();
	}

	// ==========================================
	// 3. BUSCAR ENTREGA
	// ==========================================
	private void buscarEntrega() {

		System.out.println("\n========== BUSCAR ENTREGA ==========");
		UUID id = lerUUID("Informe o ID da entrega (0 para voltar): ");
		if (id == null) return;

		Entrega entrega = servicoEntrega.buscarEntregaPorId(id);
		System.out.println("\nEntrega encontrada:");
		System.out.println(entrega);
	}

	// ==========================================
	// 4. CALCULAR FRETE (Factory Method, Strategy, Proxy e Adapter)
	// ==========================================
	private void calcularFrete() {

		System.out.println("\n========== CALCULAR FRETE ==========");
		UUID id = lerUUID("Informe o ID da entrega (0 para voltar): ");
		if (id == null) return;

		Entrega entrega = servicoEntrega.buscarEntregaPorId(id);
		CriadorCalculoFrete criador = obterCriador(entrega.getTipo());
		CalculoFrete estrategia = criador.criarCalculoFrete();
		CalculoFrete proxy = new CalculoFreteProxy(estrategia);

		double valor = servicoCalculoFrete.calcular(entrega, proxy);
		System.out.println("\nCálculo realizado com sucesso!");
		System.out.printf("Valor do frete: R$ %.2f%n", valor);
	}

	private CriadorCalculoFrete obterCriador(TipoEntrega tipo) {

		switch (tipo) {
		case ECONOMICA:
			return new CriadorFreteEconomico();

		case EXPRESSA:
			return new CriadorFreteExpresso();

		case AGENDADA:
			return new CriadorFreteAgendado();

		case TERCEIRIZADA:
			return new CriadorFreteTerceirizado();

		default:
			throw new IllegalArgumentException("Tipo de entrega não reconhecido.");
		}
	}

	// ==========================================
	// 5. ATUALIZAR STATUS (Observer)
	// ==========================================
	private void atualizarStatus() {

		System.out.println("\n========== ATUALIZAR STATUS ==========");
		UUID id = lerUUID("Informe o ID da entrega (0 para voltar): ");
		if (id == null)
			return;

		Entrega entrega = servicoEntrega.buscarEntregaPorId(id);
		System.out.println("\nStatus atual: " + entrega.getStatus());
		System.out.println("\n--- Novo status ---");

		System.out.println("1. Aguardando");
		System.out.println("2. Em trânsito");
		System.out.println("3. Entregue");
		System.out.println("4. Cancelada");

		int opcao = lerInteiro("Escolha o novo status: ");
		StatusEntrega novoStatus;

		switch (opcao) {
		case 1:
			novoStatus = StatusEntrega.AGUARDANDO;
			break;

		case 2:
			novoStatus = StatusEntrega.EM_TRANSITO;
			break;

		case 3:
			novoStatus = StatusEntrega.ENTREGUE;
			break;

		case 4:
			novoStatus = StatusEntrega.CANCELADA;
			break;

		default:
			throw new IllegalArgumentException("Status inválido.");
		}

		servicoEntrega.atualizarStatusEntrega(entrega, novoStatus);
		System.out.println("\nStatus atualizado com sucesso!");
	}

	// ==========================================
	// 6. GERENCIAR NOTIFICAÇÕES (Observer)
	// ==========================================
	private void gerenciarNotificacoes() {

		int opcao;

		do {
			System.out.println("\n========== GERENCIAR NOTIFICAÇÕES ==========");

			System.out.println("1. Ativar notificação no console");
			System.out.println("2. Desativar notificação no console");
			System.out.println("3. Ativar histórico");
			System.out.println("4. Desativar histórico");
			System.out.println("5. Consultar histórico");
			System.out.println("0. Voltar");
			System.out.println("--------------------------------------------");

			System.out.println("Observer de console: " + (consoleObserverAtivo ? "Ativo" : "Inativo"));

			System.out.println("Observer de histórico: " + (historicoObserverAtivo ? "Ativo" : "Inativo"));

			opcao = lerInteiro("Escolha uma opção: ");
			switch (opcao) {
			case 1:
				if (!consoleObserverAtivo) {
					servicoEntrega.adicionarObserver(consoleObserver);
					consoleObserverAtivo = true;

					System.out.println("Notificação no console ativada.");
				} else {
					System.out.println("Já está ativada.");
				}

				break;

			case 2:
				if (consoleObserverAtivo) {
					servicoEntrega.removerObserver(consoleObserver);
					consoleObserverAtivo = false;

					System.out.println("Notificação no console desativada.");
				} else {
					System.out.println("Já está desativada.");
				}

				break;

			case 3:
				if (!historicoObserverAtivo) {
					servicoEntrega.adicionarObserver(historicoObserver);
					historicoObserverAtivo = true;

					System.out.println("Histórico ativado.");
				} else {
					System.out.println("Já está ativado.");
				}

				break;

			case 4:
				if (historicoObserverAtivo) {
					servicoEntrega.removerObserver(historicoObserver);
					historicoObserverAtivo = false;

					System.out.println("Histórico desativado.");
				} else {
					System.out.println("Já está desativado.");
				}

				break;

			case 5:
				historicoObserver.listarHistorico();
				break;

			case 0:
				break;

			default:
				System.out.println("Opção inválida.");
			}

		} while (opcao != 0);
	}

	// ==========================================
	// MÉTODOS AUXILIARES DE ENTRADA
	// ==========================================
	private String lerTexto(String mensagem) {

		System.out.print(mensagem);
		String valor = scanner.nextLine().trim();

		if (valor.isEmpty()) {
			throw new IllegalArgumentException("O campo não pode ficar vazio.");
		}

		return valor;
	}

	private int lerInteiro(String mensagem) {
		while (true) {
			System.out.print(mensagem);

			try {
				return Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Digite um número inteiro válido.");
			}
		}
	}

	private double lerDoublePositivo(String mensagem) {
		while (true) {
			System.out.print(mensagem);

			try {
				String entrada = scanner.nextLine().trim().replace(",", ".");

				double valor = Double.parseDouble(entrada);

				if (Double.isFinite(valor) && valor > 0) {
					return valor;
				}

				System.out.println("Digite um valor positivo válido.");
			} catch (NumberFormatException e) {
				System.out.println("Digite um número válido.");
			}
		}
	}

	private UUID lerUUID(String mensagem) {
		while (true) {
			System.out.print(mensagem);

			String entrada = scanner.nextLine().trim();
			if (entrada.equals("0") || entrada.equals("")) {
				return null;
			}

			try {
				return UUID.fromString(entrada);

			} catch (IllegalArgumentException e) {
				System.out.println("UUID inválido. Tente novamente.");
			}
		}
	}
}