import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Empresa empresa = new Empresa();
        Scanner sc = new Scanner(System.in);
        int opcao;

        try {
            Secretaria s = new Secretaria("João", 3000.0, "17534557089", LocalDate.of(2022, 1, 10), true);
            Gerente g = new Gerente("Maria", 4000.0, "17532257090", LocalDate.of(2021, 3, 15), "senha123");
            Vendedor v = new Vendedor("Carlos", 4000.0, "1753355079", LocalDate.of(2023, 6, 20));

            empresa.adicionarFuncionario(s);
            empresa.adicionarFuncionario(g);
            empresa.adicionarFuncionario(v);

        } catch (CpfInvalidoException e) {
            System.out.println("Erro ao adicionar funcionário: " + e.getMessage());
        }

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Cadastrar Funcionário (Secretaria)");
            System.out.println("2 - Remover Funcionário");
            System.out.println("3 - Listar Funcionários");
            System.out.println("4 - Buscar Funcionário por CPF");
            System.out.println("5 - Autenticar Gerente");
            System.out.println("6 - Exibir Folha Mensal");
            System.out.println("7 - Listar Funcionários por Período");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcao) {
                    case 1:
                        try {
                            System.out.print("Nome: ");
                            String nome = sc.nextLine();
                            System.out.print("CPF: ");
                            String cpf = sc.nextLine();
                            System.out.print("Salário: ");
                            double salario = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("Data de admissão (AAAA-MM-DD): ");
                            LocalDate data = LocalDate.parse(sc.nextLine());
                            System.out.print("Possui idioma adicional (true/false): ");
                            boolean idioma = sc.nextBoolean();

                            Funcionario novo = new Secretaria(nome, salario, cpf, data, idioma);
                            empresa.adicionarFuncionario(novo);
                            System.out.println("Funcionário cadastrado!");
                        } catch (CpfInvalidoException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 2:
                        try {
                            System.out.print("CPF do funcionário a remover: ");
                            String cpfRemover = sc.nextLine();
                            empresa.removerFuncionario(cpfRemover);
                            System.out.println("Funcionário removido.");
                        } catch (FuncionarioNaoEncontradoException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 3:
                        empresa.listarFuncionarios();
                        break;

                    case 4:
                        try {
                            System.out.print("CPF para buscar: ");
                            String cpfBuscar = sc.nextLine();
                            Funcionario buscado = empresa.buscarFuncionarioPorCPF(cpfBuscar);
                            System.out.println("Encontrado: " + buscado.getNome());
                        } catch (FuncionarioNaoEncontradoException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 5:
                        try {
                            System.out.print("CPF do gerente: ");
                            String cpfGerente = sc.nextLine();
                            Funcionario func = empresa.buscarFuncionarioPorCPF(cpfGerente);
                            if (func instanceof Gerente g) {
                                System.out.print("Digite a senha: ");
                                String senha = sc.nextLine();
                                if (g.autenticar(senha)) {
                                    System.out.println("Senha correta!");
                                } else {
                                    System.out.println("Senha incorreta!");
                                }
                            } else {
                                System.out.println("Esse CPF não pertence a um gerente.");
                            }
                        } catch (FuncionarioNaoEncontradoException e) {
                            System.out.println("Erro: " + e.getMessage());
                        }
                        break;

                    case 6:
                        System.out.println("Folha mensal total: " + empresa.calcularFolhaMensal());
                        break;

                    case 7:
                        try {
                            System.out.print("Data inicial (AAAA-MM-DD): ");
                            LocalDate inicio = LocalDate.parse(sc.nextLine());
                            System.out.print("Data final (AAAA-MM-DD): ");
                            LocalDate fim = LocalDate.parse(sc.nextLine());
                            empresa.listarFuncionariosPorPeriodo(inicio, fim);
                        } catch (DataInvalidaException e) {
                            System.out.println("Erro: " + e.getMessage());
                        } catch (Exception e) {
                            System.out.println("Data inválida! Use o formato AAAA-MM-DD.");
                        }
                        break;

                    case 0:
                        System.out.println("Saindo...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (opcao != 0);

        sc.close();
    }
}